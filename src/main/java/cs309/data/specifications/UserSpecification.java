//package cs309.data.specifications;
//
//import cs309.data.User;
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
//public class UserSpecification implements Specification<User> {
//
//    private String queryString;
//
//    public UserSpecification(String queryString) {
//        this.queryString = queryString.toLowerCase();
//    }
//
//    @Override
//    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//        if(StringUtils.isBlank(queryString)) {
//            return null;
//        }
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.like(cb.lower(root.get("firstName")), queryString + "%"));
////        predicates.add(cb.like(root.get("lastName"), queryString + "%"));
//        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//    }
//}
