package com.example.method2023.Controllers;

import com.example.method2023.Dtos.ItemDTO;
import com.example.method2023.Dtos.UserDTO;
import com.example.method2023.Entity.Item;
import com.example.method2023.Entity.User;
import com.example.method2023.Services.ItemService;
import com.example.method2023.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController {
    private UserService userService;
    private ItemService itemService;
    public AuthController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home() {
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

    @GetMapping("/item")
    public String itemsPage(Model model){
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
        return "item";
    }
    @GetMapping("/newItem")
    public String newItemPage(Model model){
        model.addAttribute("itemDTO", new ItemDTO());
        return "newItem";
    }
    @PostMapping("/newItem/save")
    public String saveNewItem(@ModelAttribute ItemDTO itemDTO){
        itemService.saveItem(itemDTO);
        return "redirect:/newItem";
    }
}