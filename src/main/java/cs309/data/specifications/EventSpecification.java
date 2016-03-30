//package cs309.data.specifications;
//
//import cs309.data.Event;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EventSpecification implements Specification<Event> {
//
//    private String queryString;
//
//    public EventSpecification(String queryString) {
//        this.queryString = queryString;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//        if(StringUtils.isBlank(queryString)) {
//            return null;
//        }
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.like(root.get("eventName"), "%" + queryString + "%"));
//        predicates.add(cb.like(root.get("location"), "%" + queryString + "%"));
//        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//    }
//}
