package cs309.service;

import cs309.data.Tag;
import cs309.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTagsEnabled() {
        return tagRepository.getTagsEnabled();
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    public Tag getTagById(int id) {
        return tagRepository.findOne(id);
    }

}
