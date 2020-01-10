package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.misc.Cvadruple;
import org.transexpress.snap.misc.Pair;
import org.transexpress.snap.misc.ResponseMessage;
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
    public ResponseMessage addJob(@Valid @NonNull @RequestBody Job job) {
        return jobService.addJob(job);
    }

    @GetMapping
    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping(path = "jobsForUserId/{userId}")
    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllJobsForUserId(@PathVariable("userId")int id) {
        return jobService.getAllJobsForUserId(id);
    }

    @GetMapping(path = "{date}/{min-price}/{max-price}/{transport-tag}/{search-filters}")
    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllFilteredJobs(@PathVariable("date") String date,
                                                                                @PathVariable("min-price") int minPrice,
                                                                                @PathVariable("max-price") int maxPrice,
                                                                                @PathVariable("transport-tag") String transportTag,
                                                                                @PathVariable("search-filters") String searchFilters) {
        return jobService.getAllFilteredJobs(date, minPrice, maxPrice, transportTag, searchFilters);
    }

    @GetMapping(path = "{id}")
    public Cvadruple<Job, User, Float, List<JobPhoto>> getJobByID(@PathVariable("id") int id) {
        return jobService.getJobByID(id);
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
