package org.zerock.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Comments;
import org.zerock.myapp.domain.Users;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

} // end interface
