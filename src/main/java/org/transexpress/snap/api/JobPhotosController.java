package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.model.JobPhotos;
import org.transexpress.snap.model.Order;
import org.transexpress.snap.service.JobPhotosService;

import javax.validation.Valid;
import java.util.List;

public class JobPhotosController {
    public final JobPhotosService jobPhotosService;

    @Autowired
    public JobPhotosController(JobPhotosService jobPhotosService) {
        this.jobPhotosService = jobPhotosService;
    }

    @PostMapping
    public void addOrder(@Valid @NonNull @RequestBody JobPhotos jobPhotos) {
        jobPhotosService.addJobPhotos(jobPhotos);
    }

    @DeleteMapping(path = "{id}")
    public void deleteJobPhotosByID(@PathVariable("id") int id) {
        jobPhotosService.removeJobPhoto(id);
    }

    @GetMapping(path = "{id}")
    public List<JobPhotos> getAllJobPhotos(int jobId) {
        return jobPhotosService.getAllJobPhotos(jobId);
    }

}
