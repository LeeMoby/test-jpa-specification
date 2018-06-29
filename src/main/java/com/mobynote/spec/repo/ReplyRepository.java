package com.mobynote.spec.repo;

import com.mobynote.spec.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>, JpaSpecificationExecutor<Reply> {
}
