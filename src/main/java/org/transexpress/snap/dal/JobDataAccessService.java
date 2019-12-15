package org.transexpress.snap.dal;


import org.springframework.stereotype.Repository;
import org.transexpress.snap.model.Job;
import org.transexpress.snap.service.JobService;

import java.util.List;
import java.util.Optional;

@Repository("mysql")
public class JobDataAccessService implements JobDal {

    @Override
    public int insertJob(Job job) {
        return 0;
    }

    @Override
    public List<Job> selectAllJobs() {
        return null;
    }

    @Override
    public Optional<Job> selectJobByID(int id) {
        return Optional.empty();
    }

    @Override
    public int deleteJobByID(int id) {
        return 0;
    }

    @Override
    public int updateJobByID(int id, Job job) {
        return 0;
    }
}
