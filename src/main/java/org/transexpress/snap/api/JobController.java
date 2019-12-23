package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.misc.Cvadruple;
import org.transexpress.snap.misc.Pair;
import org.transexpress.snap.misc.Tuple;
import org.transexpress.snap.model.Job;
import org.transexpress.snap.model.JobPhoto;
import org.transexpress.snap.model.Message;
import org.transexpress.snap.model.User;
import org.transexpress.snap.service.JobService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/job")
@RestController
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @PostMapping
    public void addJob(@Valid @NonNull @RequestBody Job job) {
        jobService.addJob(job);
    }

    @GetMapping
    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping(path = "{date}/{min-price}/{max-price}/{transport-tag}")
    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllFilteredJobs(@PathVariable("date") String date,
                                                                                @PathVariable("min-price") int minPrice,
                                                                                @PathVariable("max-price") int maxPrice,
                                                                                @PathVariable("transport-tag") String transportTag) {
        return jobService.getAllFilteredJobs(date, minPrice, maxPrice, transportTag);
    }

    @GetMapping(path = "{id}")
    public Job getJobByID(@PathVariable("id") int id) {
        return jobService.getJobByID(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteJobByID(@PathVariable("id") int id) {

        jobService.deleteJob(id);
    }

    @PutMapping(path = "{id}")
    public void updateJobByID(@PathVariable("id") int id, @Valid @NonNull @RequestBody Job jobToUpdate) {
        jobService.updateJob(id, jobToUpdate);
    }
}
