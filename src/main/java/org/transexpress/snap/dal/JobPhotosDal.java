package org.transexpress.snap.dal;

import org.transexpress.snap.model.JobPhotos;

import java.util.List;

public interface JobPhotosDal {
    int insertJobPhotos(JobPhotos jobPhotos);
    int deleteJobPhotos(int id);
    List<JobPhotos> selectAllJobPhotos();

}
