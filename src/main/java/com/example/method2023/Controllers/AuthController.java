package com.example.method2023.Controllers;


import com.example.method2023.Dtos.AddItem;
import com.example.method2023.Dtos.CartItem;
import com.example.method2023.Dtos.ItemDTO;
import com.example.method2023.Dtos.UserDTO;
import com.example.method2023.Entity.Cart;
import com.example.method2023.Entity.User;
import com.example.method2023.Services.ItemService;
import com.example.method2023.Services.RoleService;
import com.example.method2023.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class AuthController {
    private UserService userService;
    private ItemService itemService;
    private RoleService roleService;

    public AuthController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (Objects.isNull(cart)) session.setAttribute("cart", new Cart());
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDTO userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/shop")
    public String itemsPage(Model model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (Objects.isNull(cart)) session.setAttribute("cart", new Cart());
        List<ItemDTO> itemDTOS = itemService.getAllItems()
                .stream()
                .map(item -> ItemDTO.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .description(item.getDescription())
                        .itemPicture(item.getItemPicture())
                        .price(item.getPrice())
                        .build())
                .toList();
        model.addAttribute("items", itemDTOS);
        model.addAttribute("addItem", new AddItem());
        return "shop";
    }

    @GetMapping("/newItem")
    public String newItemPage(Model model) {
        model.addAttribute("itemDTO", new ItemDTO());
        return "newItem";
    }

    @PostMapping("/newItem/save")
    public String saveNewItem(@ModelAttribute ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
        return "redirect:/newItem";
    }

    @PostMapping("users/delete")
    public String userList() {
        return "redirect:/users?success";
    }

    @PostMapping("/add")
    public String addItemToCart(@ModelAttribute AddItem addItem, HttpSession session) {
        if (Objects.isNull(addItem)) return "redirect://item?error";
        Cart userCart = (Cart) session.getAttribute("cart");
        if (userCart == null) {
            userCart = new Cart();
        }
        userCart.addItem(new CartItem(itemService.findById(addItem.getId()), addItem.getQuantity()));
        session.setAttribute("cart", userCart);
        return "redirect:/shop?success";
    }

    @GetMapping("/cart")
    public String getCartItems(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        model.addAttribute("cartItem", new CartItem());
        return "cart";
    }

    @PostMapping("changeUserRole")
    public String changeUserRole(@ModelAttribute int id) {
        User user = (User) userService.findById(id);
        if (user.getRole().getId() == 1) {
            user.setRole(roleService.findById(2));
        } else {
            user.setRole(roleService.findById(1));
        }
        return "redirect:/users";
    }

}
