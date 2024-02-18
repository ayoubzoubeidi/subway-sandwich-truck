package com.example.subwaysandwichtruck;

import com.example.subwaysandwichtruck.domain.ApplicationUser;
import com.example.subwaysandwichtruck.domain.Ingredient;
import com.example.subwaysandwichtruck.domain.IngredientType;
import com.example.subwaysandwichtruck.domain.Role;
import com.example.subwaysandwichtruck.repository.ApplicationUserRepository;
import com.example.subwaysandwichtruck.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BootStrapApplication implements CommandLineRunner {


    private final IngredientRepository ingredientRepository;

    private final ApplicationUserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (ingredientRepository.count() < 1) {
            var ingredient = ingredientRepository.saveAndFlush(Ingredient.builder()
                    .ingredientType(IngredientType.builder().name("meat").build()).name("Lamb meat")
                    .price(new BigDecimal("55")).availableQuantity(5).build());
            System.out.println("The available ingredient id is : " + ingredient.getId());
        }

        if (userRepository.count() < 1) {

            var user = userRepository.saveAndFlush(ApplicationUser.builder().firstName("USER").lastName("USER").phoneNumber("588888")
                    .role(Role.USER)
                    .build());
            System.out.println("The user id is : " + user.getId());
            var admin = userRepository.saveAndFlush(ApplicationUser.builder().firstName("ADMIN").lastName("ADMIN").phoneNumber("588888")
                    .role(Role.ADMIN)
                    .build());
            System.out.println("The admin id is : " + admin.getId());
        }
    }
}
