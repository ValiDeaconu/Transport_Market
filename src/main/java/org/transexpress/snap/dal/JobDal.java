package org.transexpress.snap.dal;

import org.transexpress.snap.model.Job;

import java.util.List;
import java.util.Optional;

public interface JobDal {
    int insertJob(Job job);

    List<Job> selectAllJobs();

    Optional<Job> selectJobByID(int id);

    int deleteJobByID(int id);

    int updateJobByID(int id, Job job);
}
