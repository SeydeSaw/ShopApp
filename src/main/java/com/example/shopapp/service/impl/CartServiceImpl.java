package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.*;
import com.example.shopapp.domain.enums.CartStatus;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.mapper.CartMapper;
import com.example.shopapp.repository.*;
import com.example.shopapp.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    @Transactional
    @Override
    public Cart createNewCart(CartDto cartDto) {
        Cart cart = new Cart();
        User client = userRepository.findById(cartDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not find"));
        cart.setClient(client);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        cart.setStatus(CartStatus.NEW);
        cartRepository.save(cart);
        return cart;
    }

    @Transactional
    @Override
    public CartDto getById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not find"));
        return cartMapper.mapToDto(cart);
    }

    @Transactional
    @Override
    public CartDto updateById(CartDto cartDto, Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not find"));
        updateCartNewData(cartDto, cart);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
        return convertToCartDto(cart);
    }

    @Transactional
    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CartDto addProductToCart(OrderDetailDto orderDetailDto, Long userId) {
        Optional<Cart> activeCart = cartRepository.getCartByUserId(userId);
        Cart cart;
        if (activeCart.isPresent()) {
            cart = activeCart.get();
        } else {
            cart = new Cart();
            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not find"));
            cart.setClient(user);
            cart.setStatus(CartStatus.ACTIVE);
            cart.setCreatedAt(LocalDateTime.now());
            cart.setUpdatedAt(LocalDateTime.now());
            cartRepository.save(cart);
        }
        createNewOrderDetail(orderDetailDto, cart);
        return cartMapper.mapToDto(cart);
    }

    @Transactional
    @Override
    public CartDto completeCartOfUser(Long userId) {
        Cart cart = cartRepository.getCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Active cart of user not find"));
        cart.setStatus(CartStatus.COMPLETED);
        cart.setUpdatedAt(LocalDateTime.now());
        createNewOrder(cart);
        return cartMapper.mapToDto(cart);
    }

    private void createNewOrder(Cart cart) {
        Order order = new Order();
        order.setCart(cart);
        BigDecimal totalPrice = cart.getOrderDetails().stream()
                .map(orderDetail -> orderDetail.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(orderDetail.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        order.setTotalPrice(totalPrice);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    private void createNewOrderDetail(OrderDetailDto orderDetailDto, Cart cart) {
        Product product = productRepository.findById(orderDetailDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not find"));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCart(cart);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetailRepository.save(orderDetail);

        cart.getOrderDetails().add(orderDetail);
    }

    private static CartDto convertToCartDto(Cart cart) {
        CartDto newCartDto = new CartDto();
        newCartDto.setClientId(cart.getClient().getId());
        return newCartDto;
    }

    private void updateCartNewData(CartDto cartDto, Cart cart) {
        if (cartDto.getClientId() != null && !cartDto.getClientId().equals(cart.getClient().getId())) {
            User client = userRepository.findById(cartDto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not find"));
            cart.setClient(client);
        }
    }
}