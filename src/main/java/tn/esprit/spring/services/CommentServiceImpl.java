package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.repository.CommentRepository;

public class CommentServiceImpl  implements ICommentService{

	@Autowired
	CommentRepository  commentrepository;
	
	
	@Override
	public Comment AddComment(Comment comment) {
		commentrepository.save(comment);	
	
		return comment;
	}
	
	
	@Override
	public List<Comment> retrieveAllComments() {
		List<Comment> comments=(List<Comment>) commentrepository.findAll();
		return comments;	
		}

	@Override
	public Comment updateComment(Comment comment) {
		commentrepository.save(comment);
		return comment;
	}

	@Override
	public void deleteComment(int IdComment) {
		commentrepository.deleteById((long) IdComment);
	}

	
}
