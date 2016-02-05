package cs309.service;

import cs309.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chasekoehler on 2/1/16.
 */
public class CommentService {

    @Autowired
    CommentRepository eventRepository;
}
