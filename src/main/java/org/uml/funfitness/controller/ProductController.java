package org.uml.funfitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uml.funfitness.pojo.Equipment;
import org.uml.funfitness.pojo.Product;
import org.uml.funfitness.service.ProductService;
import org.uml.funfitness.pojo.Member;
import org.uml.funfitness.service.MemberService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/shop")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberService memberService;

    //查询商品目录
    @RequestMapping("/catalog")
    public List<Product> getAllProducts() {
        List<Product> products = productService.findAll();
        return products;
    }


    //新增器材
    @PostMapping("/AddProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return ResponseEntity.ok("Product added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product");
        }
    }

    //下架商品
    @RequestMapping("DeleteProduct")
    public String DeleteProduct(int product_id) {
        productService.deleteProduct(product_id);
        return "redirect:/shop/catalog";
    }

    @PostMapping("/UpdateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product);
            return ResponseEntity.ok("Product updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product");
        }
    }

    //购买商品
    @PostMapping("/buyProduct")
    public ResponseEntity<Map<String, Object>> buyProduct(@RequestParam Integer productId, @RequestParam Integer memberAccount) {
        Map<String, Object> result = new HashMap<>();

        // 获取商品信息
        Product product = productService.getProductById(productId);
        if (product == null) {
            result.put("success", false);
            result.put("message", "商品不存在");
            return ResponseEntity.ok(result);
        }

        // 获取用户信息
        List<Member> members = memberService.findByAccount(memberAccount);
        if (members.isEmpty()) {
            result.put("success", false);
            result.put("message", "用户不存在");
            return ResponseEntity.ok(result);
        }
        Member member = members.get(0);

        // 判断用户积分是否足够
        if (member.getMemberCredit() < product.getProduct_price()) {
            result.put("success", false);
            result.put("message", "用户积分不足");
            return ResponseEntity.ok(result);
        }

        // 判断商品库存是否足够
        if (product.getProduct_quantity() < 1) {
            result.put("success", false);
            result.put("message", "商品库存不足");
            return ResponseEntity.ok(result);
        }

        // 执行购买操作
        member.setMemberCredit(member.getMemberCredit() - product.getProduct_price());
        product.setProduct_quantity(product.getProduct_quantity() - 1);

        // 更新数据库
        memberService.updateMemberByMemberAccount(member);
        productService.updateProduct(product);

        result.put("success", true);
        result.put("message", "购买成功");
        return ResponseEntity.ok(result);
    }
}
