package ceu.dam.ad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.model.Question;
import ceu.dam.ad.model.QuestionType;
import ceu.dam.ad.repository.QuestionRepository;
import ceu.dam.ad.repository.QuestionTypeRepository;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository repoQuestion;
	
	@Autowired
	private QuestionTypeRepository repoQuestionType;
	
	
	@Override
	public Question createQuestion(Question question) throws QuestionValidateException {
		if (!question.validate()) {
			throw new QuestionValidateException("El formato de la pregunt ano existe");
		}
		return repoQuestion.save(question);
		
	}

	@Override
	public List<Question> getAllQuestions() {
		return repoQuestion.findAll();
	}

	@Override
	public List<QuestionType> getAllQuestionTypes() {
		return repoQuestionType.findAll();
	}

	@Override
	public List<Question> getAllQuestionByType(String typeCode) {
		return repoQuestion.findAllByTypeCodeCode(typeCode);
	}

	@Override
	public Question getQuestionById(Long id) throws QuestionNotFoundException {
		Optional<Question> question = repoQuestion.findById(id);
		if (question.isEmpty()) {
			throw new QuestionNotFoundException("Question with this id doesn´t exists");
		}
		return question.get();
	}

	@Override
	public Question updateQuestion(Question question) throws QuestionNotFoundException, QuestionValidateException {
		if (!question.validate()) {
			throw new QuestionValidateException("El formato de la pregunt ano existe");
		}
		
		return repoQuestion.save(question);
	}

	@Override
	public void deleteQuestion(Long id) {
		
	}

}
