package org.transexpress.snap.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import ch.qos.logback.core.util.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.JobDal;
import org.transexpress.snap.misc.Checker;
import org.transexpress.snap.misc.Cvadruple;
import org.transexpress.snap.misc.ResponseMessage;
import org.transexpress.snap.model.Job;
import org.transexpress.snap.model.JobPhoto;
import org.transexpress.snap.model.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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

    public ResponseMessage addJob(Job job) {
        int code = jobDal.insertJob(job);
        if (code < 0){
            return new ResponseMessage("Database error", -1);
        } else return new ResponseMessage("Job added successfully", code);
    }

    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllJobs() {
        List<Job> jobs = jobDal.selectAllJobs();

        List<Cvadruple<Job, User, Float, List<JobPhoto>>> result = new ArrayList<>();

        if (jobs == null)
            return result;

        for (Job job : jobs) {
            Optional<User> user = userService.getUserByID(job.getOwnerId());
            float userRate = userReviewService.getAverageRateForUserId(job.getOwnerId());
            List<JobPhoto> jobPhotos = jobPhotoService.getAllJobPhotosForJobId(job.getId());

            user.ifPresent(value -> result.add(new Cvadruple<>(job, value, userRate, jobPhotos)));
        }

        return result;
    }

    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllJobsForUserId(int id) {
        List<Job> jobs = jobDal.selectAllJobsForUserId(id);

        List<Cvadruple<Job, User, Float, List<JobPhoto>>> result = new ArrayList<>();

        for (Job job : jobs) {
            Optional<User> user = userService.getUserByID(job.getOwnerId());
            float userRate = userReviewService.getAverageRateForUserId(job.getOwnerId());
            List<JobPhoto> jobPhotos = jobPhotoService.getAllJobPhotosForJobId(job.getId());

            user.ifPresent(value -> result.add(new Cvadruple<>(job, value, userRate, jobPhotos)));
        }

        return result;
    }

    public List<Cvadruple<Job, User, Float, List<JobPhoto>>> getAllFilteredJobs(String date,
                                                                                int minPrice,
                                                                                int maxPrice,
                                                                                String transportTag,
                                                                                String searchFilters) {
        List<Job> jobs = jobDal.selectAllJobs();

        List<Job> filteredJobs = jobs.stream()
                .filter(job -> {
                    boolean ok = true;

                    if (!date.equals("__empty__"))
                        ok = Checker.getInstance().isSameDay(date, job.getDepartureDate());

                    ok = ok && job.getPrice() >= minPrice;
                    ok = ok && job.getPrice() <= maxPrice;

                    if (!transportTag.equals("__empty__"))
                        ok = ok && Checker.getInstance().tagListContainsTag(job.getTags(), transportTag);

                    if (!searchFilters.equals("__empty__")) {
                        boolean matched = Checker.getInstance().jobMatchesSearchFilters(job, searchFilters);

                        if (!matched) {
                            User user = userService.getUserByID(job.getOwnerId()).orElse(null);
                            if (user != null) {
                                matched = Checker.getInstance().userMatchesSearchFilters(user, searchFilters);
                            }
                        }

                        ok = ok && matched;
                    }

                    return ok;
                })
                .collect(Collectors.toList());

        List<Cvadruple<Job, User, Float, List<JobPhoto>>> result = new ArrayList<>();

        for (Job job : filteredJobs) {
            Optional<User> user = userService.getUserByID(job.getOwnerId());
            float userRate = userReviewService.getAverageRateForUserId(job.getOwnerId());
            List<JobPhoto> jobPhotos = jobPhotoService.getAllJobPhotosForJobId(job.getId());

            user.ifPresent(value -> result.add(new Cvadruple<>(job, value, userRate, jobPhotos)));
        }

        return result;
    }


    public Cvadruple<Job, User, Float, List<JobPhoto>> getJobByID(int id) {
        Optional<Job> jobOpt = jobDal.selectJobByID(id);
        Job job;
        if (jobOpt.isPresent()){
            job = jobOpt.get();
        } else { return null; }

        Cvadruple<Job, User, Float, List<JobPhoto>> result;

        Optional<User> user = userService.getUserByID(job.getOwnerId());
        float userRate = userReviewService.getAverageRateForUserId(job.getOwnerId());
        List<JobPhoto> jobPhotos = jobPhotoService.getAllJobPhotosForJobId(job.getId());
        if (user.isPresent()){
            result = new Cvadruple<Job, User, Float, List<JobPhoto>>(job, user.get(), userRate, jobPhotos);
            return result;
        } else return null;

    }

    public int deleteJob(int id) {

        return jobDal.deleteJobByID(id);
    }

    public int updateJob(int id, Job newJob) {

        return jobDal.updateJobByID(id, newJob);
    }
}
