package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.JobPhotoDal;
import org.transexpress.snap.misc.ResponseMessage;
import org.transexpress.snap.model.JobPhoto;
import org.transexpress.snap.model.JobPhotos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPhotoService {
    private final JobPhotoDal jobPhotoDal;

    public JobPhotoService(@Qualifier("mysql_job_photos") JobPhotoDal jobPhotoDal) {
        this.jobPhotoDal = jobPhotoDal;
    }

    public ResponseMessage addJobPhotos(JobPhoto jobPhoto){
        if (jobPhotoDal.insertJobPhoto(jobPhoto) != 1)
            return new ResponseMessage("Database error", -1);
        else return new ResponseMessage("JobPhoto inserted successfully", 0);
    }

    public ResponseMessage addJobPhotos(JobPhotos jobPhotos) {
        int successfullyAdded = 0;

        String[] links = jobPhotos.getLinks().split(";");

        for (String link : links) {
            System.out.println(link);

            JobPhoto jobPhoto = new JobPhoto(-1, link, jobPhotos.getJobId());

            if (jobPhotoDal.insertJobPhoto(jobPhoto) != 1) {
                break;
            }

            successfullyAdded++;
        }

        if (successfullyAdded < links.length)
            return new ResponseMessage("Only " + successfullyAdded + " photos were added", -1);
        return new ResponseMessage(successfullyAdded + " photos were inserted successfully", 0);
    }

    public int removeJobPhoto(int id){
        return jobPhotoDal.deleteJobPhoto(id);
    }

    public List<JobPhoto> getAllJobPhotosForJobId(int jobId){
        List<JobPhoto> allJobPhotos = jobPhotoDal.selectAllJobPhotos();

        if (allJobPhotos == null)
            return new ArrayList<>();

        List<JobPhoto> result = allJobPhotos.stream()
                .filter(o -> o.getJobId() == jobId)
                .collect(Collectors.toList());

        return result;
    }
}
