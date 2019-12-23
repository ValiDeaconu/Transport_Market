package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.JobDal;
import org.transexpress.snap.misc.Cvadruple;
import org.transexpress.snap.model.Job;
import org.transexpress.snap.model.JobPhoto;
import org.transexpress.snap.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    // dal
    private final JobDal jobDal;

    // services
    private final UserService userService;
    private final UserReviewService userReviewService;
    private final JobPhotoService jobPhotoService;

    @Autowired
    public JobService(@Qualifier("mysql_jobs") JobDal jobDal,
                      UserService userService,
                      UserReviewService userReviewService,
                      JobPhotoService jobPhotoService) {
        this.jobDal = jobDal;
        this.userService = userService;
        this.userReviewService = userReviewService;
        this.jobPhotoService = jobPhotoService;
    }

    public int addJob(Job job) {

        return jobDal.insertJob(job);
    }

    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllJobs() {
        List<Job> jobs = jobDal.selectAllJobs();

        List<Cvadruple<Job, User, Float, List<JobPhoto>>> result = new ArrayList<>();

        for (Job job : jobs) {
            Optional<User> user = userService.getUserByID(job.getOwnerId());
            float userRate = userReviewService.getAverageRateForUserId(job.getOwnerId());
            List<JobPhoto> jobPhotos = jobPhotoService.getAllJobPhotosForJobId(job.getId());

            user.ifPresent(value -> result.add(new Cvadruple<>(job, value, userRate, jobPhotos)));
        }

        return result;
    }

    public Optional<Job> getJobByID(int id) {

        return jobDal.selectJobByID(id);
    }

    public int deleteJob(int id) {

        return jobDal.deleteJobByID(id);
    }

    public int updateJob(int id, Job newJob) {

        return jobDal.updateJobByID(id, newJob);
    }
}
