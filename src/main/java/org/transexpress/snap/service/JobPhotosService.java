package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.transexpress.snap.dal.JobPhotosDal;
import org.transexpress.snap.model.JobPhotos;
import org.transexpress.snap.model.UserReviews;

import java.util.List;
import java.util.stream.Collectors;

public class JobPhotosService {
    private final JobPhotosDal jobPhotosDal;

    public JobPhotosService(@Qualifier("mysql_job_photos") JobPhotosDal jobPhotosDal) {
        this.jobPhotosDal = jobPhotosDal;
    }

    public int addJobPhotos(JobPhotos jobPhotos){
        return jobPhotosDal.insertJobPhotos(jobPhotos);
    }

    public int removeJobPhoto(int id){
        return jobPhotosDal.deleteJobPhotos(id);
    }

    public List<JobPhotos> getAllJobPhotos(int jobId){
        List<JobPhotos> allJobPhotos= jobPhotosDal.selectAllJobPhotos();

        List<JobPhotos> result = allJobPhotos.stream()
                .filter(o -> o.getJobId() == jobId)
                .collect(Collectors.toList());
        return result;
    }
}
