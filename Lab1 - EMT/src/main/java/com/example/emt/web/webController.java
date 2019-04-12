package com.example.emt.web;

import com.example.emt.models.Category;
import com.example.emt.models.Manufacturer;
import com.example.emt.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class webController {

    private Long ID;
    private List<Product> products = null;
    private List<Category> categories = null;
    private List<Manufacturer> manufacturers = null;

    @PostConstruct
    public void init(){

        ID = 0l;

        products = new ArrayList<>();
        categories = new ArrayList<>();
        manufacturers = new ArrayList<>();

        categories.add(new Category(1l, "Fudbal"));
        categories.add(new Category(2l, "Kosarka"));
        categories.add(new Category(3l, "Odbojka"));

        manufacturers.add(new Manufacturer(1l, "Nike"));
        manufacturers.add(new Manufacturer(2l, "Adidas"));
        manufacturers.add(new Manufacturer(3l, "Wilson"));

        products.add(new Product(getNextID(), "Topka 2019 new", "Perfektna za eftina cena", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Slika_nije_dostupna.svg", categories.get(0), manufacturers.get(0)));
        products.add(new Product(getNextID(), "Topka 2011 new", "Perfektna za skapa cena", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Slika_nije_dostupna.svg", categories.get(1), manufacturers.get(1)));
        products.add(new Product(getNextID(), "Topka 2012 new", "Perfektna za sredna cena", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Slika_nije_dostupna.svg", categories.get(2), manufacturers.get(2)));
        products.add(new Product(getNextID(), "Topka 2013 new", "Perfektna za povolna cena", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Slika_nije_dostupna.svg", categories.get(0), manufacturers.get(2)));
        products.add(new Product(getNextID(), "Topka 2014 new", "Perfektna za katastrofalna cena", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Slika_nije_dostupna.svg", categories.get(2), manufacturers.get(1)));


    }

    @GetMapping("products")
    public String getMappingProducts(Model model){
        model.addAttribute("product", products);
        return "index";
    }

    @GetMapping("products/{id}")
    public String getMappingReview(@PathVariable("id") Long id ,Model model){
        model.addAttribute("product", this.products.stream().filter(i->i.getID() == id).findFirst().get());
        return "review";
    }

    @GetMapping("products/add")
    public String getMappingAdd(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("manufacturer", manufacturers);
        model.addAttribute("category", categories);
        return "add";
    }

    public Long getNextID(){
        return ID++;
    }

    @PostMapping("products/add")
    public String postMappingAdd(@ModelAttribute Product p){
        p.setManufacturer(this.manufacturers.stream().filter(i->i.getName().equals(p.getManufacturer().getName())).findFirst().get());
        p.setCategory(this.categories.stream().filter(i->i.getID() == p.getCategory().getID()).findFirst().get());
        p.setID(getNextID());
        this.products.add(p);
        return "redirect:/products/";
    }

    @DeleteMapping("products")
    public String deleteMappingProducts(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));

        for(int i=0; i<this.products.size(); i++){
            if(i == id){
                products.remove(i);
            }
        }

        ID = 0l;
        for(int i=0; i<this.products.size(); i++){products.get(i).setID(getNextID());}

        //this.products.remove(this.products.stream().filter(i->i.getID() == id).findFirst().get());
        return "redirect:/products/";
    }


}
