//package com.example.shopapp.service.impl;
//
//import com.example.shopapp.domain.entity.Cart;
//import com.example.shopapp.domain.entity.User;
//import com.example.shopapp.service.ProductService;
//import com.example.shopapp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
////
////import com.example.shopapp.domain.entity.User;
////import com.example.shopapp.repository.UserRepository;
////import com.example.shopapp.service.UserService;
////import org.slf4j.LoggerFactory;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////
////import java.util.logging.Logger;
////
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Autowired
//    private ProductService productService;
//    @Override
//    public List<User> getAll() {
//        return repository.getAll();
//    }
//
//    @Override
//    public void add(User user) {
//        repository.add(user.getName());
//    }
//
////    @Override
////    public void login(String username, String password) {
////
////    }
//
//    @Override
//    public User getById(long id) {
//        return repository.getById(id);
//    }
//
//
////    @Override
////    public User getByEmail(String email) {
////        return null;
////    }
//
//    @Override
//    public void deleteById(long id) {
//        repository.delete(id);
//    }
//
//    @Override
//    public void deleteByUsername(String username) {
//        long idToDelete = repository.getAll().stream().filter(x -> x.getName().equals(username)).findFirst().get().getId();
//        repository.delete(idToDelete);
//    }
//
//    @Override
//    public int getCount() {
//        return repository.getAll().size();
//    }
//
//    @Override
//    public double getTotalPriceById(long id) {
//        return repository.getById(id).getCart().getTotalPrice();
//    }
//
//    @Override
//    public double getAveragePriceById(long id) {
//        Cart cart = repository.getById(id).getCart();
//        return cart.getTotalPrice() / cart.getProduct().size();
//    }
//
//    @Override
//    public void addToCartById(long userId, long productId) {
//        repository.addToCartById(userId, productId);
//    }
//
//    @Override
//    public void deleteFromCart(long userId, long productId) {
//        repository.deleteFromCart(userId, productId);
//    }
//
//    @Override
//    public void clearCart(long userId) {
//        repository.clearCart(userId);
//    }
//    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
//    private final UserRepository userRepository;
//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.userDetailsService = userDetailsService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public void save(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//
//    @Override
//    public void login(String username, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        authenticationManager.authenticate(token);
//
//        if (token.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(token);
//            logger.log(String.format("User %s logged in successfully!", username));
//        }else{
//            logger.error(String.format("Error with %s authentication!", username));
//        }
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    @Override
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    @Override
//    public User findById(long id) {
//        return userRepository.findById(id);
//    }
//}