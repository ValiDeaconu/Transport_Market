package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.JobDal;
import org.transexpress.snap.model.Job;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobDal jobDal;

    @Autowired
    public JobService(@Qualifier("mysql_jobs") JobDal jobDal) {
        this.jobDal = jobDal;
    }

    public int addJob(Job job) {

        return jobDal.insertJob(job);
    }

    public List<Job> getAllJobs() {
        return jobDal.selectAllJobs();
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
