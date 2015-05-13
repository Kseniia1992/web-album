package ua.ksolodovnik.webalbum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ua.ksolodovnik.webalbum.entity.Photo;
import ua.ksolodovnik.webalbum.service.PhotoService;

import java.io.IOException;
import java.util.List;

/**
 * Picture controller class
 * @author ksolodovnik
 */

@Controller
public class ServerPhotoController {

    @Autowired
    PhotoService photoService;

    @ModelAttribute("photo")
    public Photo createPhotoList(){
        return new Photo();
    }

/*  THIS METHOD WORKS  */

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String showPhotos(Model model){
        model.addAttribute("photos", photoService.getAllPageble());
        return "/result";
    }

    /**
     * REST method sends json to client
     * @return
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Photo> showPhotosInfo(){
        return photoService.getAllPageble();
    }

    /**
     * Display upload form
     * @return
     */
    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    public String showFormUpload(){
        return "picture";
    }

    /**
     * Save photo to db
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String doUpload(MultipartFile file, @ModelAttribute("photo") Photo photo) throws IOException {
        if (file != null) {
            photo.setName(file.getOriginalFilename());
            photo.setImage(file.getBytes());
            photoService.savePhoto(photo);
        }
        return "redirect:result.html";
    }
}
