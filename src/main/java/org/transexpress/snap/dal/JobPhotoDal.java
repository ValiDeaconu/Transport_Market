package org.transexpress.snap.dal;

import org.transexpress.snap.model.JobPhoto;

import java.util.List;

public interface JobPhotoDal {
    int insertJobPhoto(JobPhoto jobPhoto);
    int deleteJobPhoto(int id);
    List<JobPhoto> selectAllJobPhotos();

}
