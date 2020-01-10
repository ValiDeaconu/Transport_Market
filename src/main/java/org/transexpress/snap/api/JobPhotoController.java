package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.misc.ResponseMessage;
import org.transexpress.snap.model.JobPhoto;
import org.transexpress.snap.service.JobPhotoService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/photos")
@RestController
public class JobPhotoController {
    public final JobPhotoService jobPhotoService;

    @Autowired
    public JobPhotoController(JobPhotoService jobPhotoService) {
        this.jobPhotoService = jobPhotoService;
    }

    @PostMapping
    public ResponseMessage addJobPhoto(@Valid @NonNull @RequestBody JobPhoto jobPhoto) {
        return jobPhotoService.addJobPhotos(jobPhoto);
    }

    @PostMapping(path = "{unId}")
    public ResponseMessage addJobPhotoAsStr(@PathVariable ("unId") int cvNuCOnt, @RequestBody JobPhoto jobphoto){
        System.out.println(jobphoto.getLink());
        return jobPhotoService.addJobPhotoAsStr(jobphoto);
    }

    @DeleteMapping(path = "{id}")
    public void deleteJobPhotosByID(@PathVariable("id") int id) {
        jobPhotoService.removeJobPhoto(id);
    }

    @GetMapping(path = "{id}")
    public List<JobPhoto> getAllJobPhotos(@PathVariable("id") int jobId) {
        return jobPhotoService.getAllJobPhotosForJobId(jobId);
    }

}
