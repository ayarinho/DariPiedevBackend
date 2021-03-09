package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Comment;

public interface ICommentService {

	public Comment AddComment(Comment comment);
	public List<Comment> retrieveAllComments();
	public Comment updateComment(Comment comment) ;
	public void deleteComment(int IdComment);
	
}
