package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.JobPhotoDal;
import org.transexpress.snap.model.JobPhoto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPhotoService {
    private final JobPhotoDal jobPhotoDal;

    public JobPhotoService(@Qualifier("mysql_job_photos") JobPhotoDal jobPhotoDal) {
        this.jobPhotoDal = jobPhotoDal;
    }

    public int addJobPhotos(JobPhoto jobPhoto){
        return jobPhotoDal.insertJobPhoto(jobPhoto);
    }

    public int removeJobPhoto(int id){
        return jobPhotoDal.deleteJobPhoto(id);
    }

    public List<JobPhoto> getAllJobPhotos(int jobId){
        List<JobPhoto> allJobPhotos= jobPhotoDal.selectAllJobPhotos();

        List<JobPhoto> result = allJobPhotos.stream()
                .filter(o -> o.getJobId() == jobId)
                .collect(Collectors.toList());
        return result;
    }
}
