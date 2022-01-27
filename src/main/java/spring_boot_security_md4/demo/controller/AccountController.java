package spring_boot_security_md4.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spring_boot_security_md4.demo.model.Account;
import spring_boot_security_md4.demo.model.Role;
import spring_boot_security_md4.demo.service.IAccountService;
import spring_boot_security_md4.demo.service.IRoleService;
import spring_boot_security_md4.demo.validate.Validate_name;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    IAccountService accountService;
    @Autowired
    IRoleService roleService;
    @Autowired
    Validate_name validate;
    @Value("${uploadPart}")
    private String uploadPart;

    @GetMapping("/account")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("showAccount");
        modelAndView.addObject("account", accountService.findAllPage(PageRequest.of(page, 2)));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("account", new Account());
        return modelAndView;
    }
    @ModelAttribute("role")
    public List<Role> show(){
        return roleService.findAll();
    }

    @PostMapping("create")
    public Object add(@Valid @ModelAttribute(value = "account") Account account, BindingResult bindingResult, @RequestParam MultipartFile uppImg) {
        validate.validate(account, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        String filename = uppImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppImg.getBytes(),new File(uploadPart+"img/" + filename));
            account.setImg("img/" +filename);
            accountService.save(account);

        } catch (IOException e) {
            account.setImg("");
            accountService.save(account);
            e.printStackTrace();
        }
        return "redirect:/account";
    }

    @GetMapping("edit")
    public ModelAndView edit(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("account", accountService.findById(id));
        return modelAndView;
    }

    @PostMapping("edit")
    public Object editUser(@Valid @ModelAttribute(value = "account") Account account, BindingResult bindingResult, @RequestParam ("uppImg") MultipartFile uppImg) {
        validate.validate(account,bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("edit");
            return modelAndView;
        }
        if (uppImg.getSize() != 0) {
            String file1 = uploadPart + account.getImg();
            File file = new File(file1);
            file.delete();
            String nameFile = uppImg.getOriginalFilename();
            try {
                FileCopyUtils.copy(uppImg.getBytes(), new File(uploadPart +"img/"+ nameFile));
                account.setImg("img/" + nameFile);
                accountService.save(account);
            } catch (IOException e) {
                account.setImg("");
                accountService.save(account);
                e.printStackTrace();
            }

        }
        return "redirect:/account";
    }

    @GetMapping("/delete")
    public ModelAndView deleteform(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("deleteacc");
        modelAndView.addObject("account", accountService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        Account account = accountService.findById(id);
        if(account.getImg().isEmpty()){
            accountService.delete(id);
            return "redirect:/account";
        }
        String filedelete = account.getImg().replaceAll("img/","");
        String file1 = uploadPart + "img/" +filedelete;
        File file = new File(file1);
        if(file.exists()){
            file.delete();
        }
        accountService.delete(id);
        return "redirect:/account";
    }

    @PostMapping("/search")
    public ModelAndView searchByName(@RequestParam (value = "search") String search) {
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("account", accountService.findByName(search));
        return modelAndView;
    }
//
//    @GetMapping("/sortByName")
//    public ModelAndView sortsalary() {
//        ModelAndView modelAndView = new ModelAndView("sort");
//        List<Account> sortByName = accountService.sortByName();
//        modelAndView.addObject("user", sortByName);
//        return modelAndView;
//    }
    @GetMapping ("detail")
    public ModelAndView detail (@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("account" , accountService.findById(id));
        return modelAndView;
    }
}
