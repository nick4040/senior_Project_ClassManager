package main.ClassManger3.Controller;

import main.ClassManger3.Entity.CourceAssignmentEntity;
import main.ClassManger3.Entity.CourceEntity;
import main.ClassManger3.Entity.CourceNavInfoEntity;
import main.ClassManger3.Repo.CourceAssignmentRepo;
import main.ClassManger3.Repo.CourceNavRepo;
import main.ClassManger3.Repo.CourceRepo;
import main.ClassManger3.Service.RoleService;
import main.ClassManger3.Service.UpdateClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class adminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UpdateClassService updateClassService;

    @Autowired
    private CourceRepo courceRepo;

    @Autowired
    private CourceAssignmentRepo courceAssignmentRepo;

    @Autowired
    private CourceNavRepo courceNavRepo;

    @GetMapping("/adminDashboard")
    public String adminDashboerd(Model model) {
        // You can add any attributes you need to your model
        return "MediaDash/adminViews/adminDashboerd"; // This will return the "index.html" template
    }

    @GetMapping("/adminAssignments")
    public String adminAssignment(Model model) {
        // You can add any attributes you need to your model
        return "MediaDash/adminViews/adminAssignments"; // This will return the "about.html" template
    }

    @GetMapping("/adminCources")
    public String adminCources(Model model) {

        List CourceIds = roleService.getCourceIDs();

        //finds the cources and outputs them
        List<CourceEntity> listCource = courceRepo.findAllById(CourceIds);
        model.addAttribute("listCource", listCource);

        // You can add any attributes you need to your model
        return "MediaDash/adminViews/adminCources"; // This will return the "index.html" template
    }

    @GetMapping("/adminGrades")
    public String adminGrades(Model model) {
        // You can add any attributes you need to your model
        return "MediaDash/adminViews/adminGrades"; // This will return the "about.html" template
    }

    @GetMapping("/adminAccount")
    public String adminAccount(Model model) {
        // You can add any attributes you need to your model
        return "MediaDash/adminViews/adminAccount"; // This will return the "index.html" template
    }

    @GetMapping("/adminUpdateClass")
    public String adminUpdateClass(Model model) {
        // You can add any attributes you need to your model
        model.addAttribute("ClassForm", new CourceEntity());
        return "MediaDash/adminViews/adminUpdateClass"; // This will return the "about.html" template
    }

    @PostMapping("/adminUpdateClass")
    public String adminUpdateClass(@ModelAttribute CourceEntity courceEntity) throws IOException {

        courceRepo.save(courceEntity);

        updateClassService.checkAndGenerateC();

        return "MediaDash/adminViews/adminSubSuc";
    }

    @GetMapping("/adminAddCategory")
    public String adminAddCategory(Model model) {

        List<CourceEntity> courceRepos1 = courceRepo.findAll();
        model.addAttribute("AllCource", courceRepos1);
        model.addAttribute("CategoryForm", new CourceNavInfoEntity());

        return "MediaDash/adminViews/adminAddCategory"; // This will return the "about.html" template
    }

    @PostMapping("/adminAddCategory")
    public String adminAddCategory(@ModelAttribute CourceNavInfoEntity courceNavInfoEntity) throws IOException {

        courceNavRepo.save(courceNavInfoEntity);

        updateClassService.createCategoryPage();

        updateClassService.updateDefaultClasses(String.valueOf(courceNavInfoEntity.getCourceID()));


        return "MediaDash/adminViews/adminSubSuc";
    }

    @GetMapping("/adminAddActivity")
    public String adminAddActivity(Model model) {
        List<CourceNavInfoEntity> courceNavInfoEntityList = courceNavRepo.findAll();
        List<CourceEntity> courceRepos1 = courceRepo.findAll();
        model.addAttribute("AllCource", courceRepos1);
        model.addAttribute("AllCat", courceNavInfoEntityList);
        model.addAttribute("courceAssignmentEntity", new CourceAssignmentEntity());
        return "MediaDash/adminViews/adminAddActivity"; // This will return the "about.html" template
    }

    @PostMapping("/adminAddActivity")
    public String adminAddActivityPost(@ModelAttribute CourceAssignmentEntity courceAssignmentEntity) throws IOException {

        courceAssignmentRepo.save(courceAssignmentEntity);

        updateClassService.createAssignmentPage();
        updateClassService.updateDefaultCategory(courceAssignmentEntity.getCategory(), String.valueOf(courceAssignmentEntity.getCourceID()));

        return "MediaDash/adminViews/adminSubSuc";
    }

    @GetMapping("/adminSch")
    public String adminSch(Model model) {
        return "MediaDash/adminViews/adminSch";
    }
}
