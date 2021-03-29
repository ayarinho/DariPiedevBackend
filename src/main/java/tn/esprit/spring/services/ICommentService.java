package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Comment;

public interface IcommentService {

	public Comment AddComment(Comment comment,long idUser);
	public List<Comment> retrieveAllComments();
	public Comment updateComment(Comment comment) ;
	public void deleteComment(Long IdComment);
	public Comment retrieveCommentById(Long id);
	
}
