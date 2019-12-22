package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
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
    public void addJobPhoto(@Valid @NonNull @RequestBody JobPhoto jobPhoto) {
        jobPhotoService.addJobPhotos(jobPhoto);
    }

    @DeleteMapping(path = "{id}")
    public void deleteJobPhotosByID(@PathVariable("id") int id) {
        jobPhotoService.removeJobPhoto(id);
    }

    @GetMapping(path = "{id}")
    public List<JobPhoto> getAllJobPhotos(int jobId) {
        return jobPhotoService.getAllJobPhotos(jobId);
    }

}
