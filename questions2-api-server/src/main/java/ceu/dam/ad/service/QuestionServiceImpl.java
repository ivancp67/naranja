package ceu.dam.ad.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.dto.NewExamRequest;
import ceu.dam.ad.model.Question;
import ceu.dam.ad.model.QuestionType;
import ceu.dam.ad.repository.QuestionRepository;
import ceu.dam.ad.repository.QuestionTypeRepository;
import jakarta.transaction.Transactional;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository repoQuestion;
	
	@Autowired
	private QuestionTypeRepository repoQuestionType;
	
	
	@Override
	@Transactional
	public Question createQuestion(Question question) throws QuestionValidateException {
		if (!question.validate()) {
			throw new QuestionValidateException("El formato de la pregunta ano existe");
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
	@Transactional
	public Question updateQuestion(Question question) throws QuestionNotFoundException, QuestionValidateException {
		if (!question.validate()) {
			throw new QuestionValidateException("El formato de la pregunta no existe");
		}
		getQuestionById(question.getId());
		return repoQuestion.save(question);
	}

	@Override
	public void deleteQuestion(Long id) {
		repoQuestion.deleteById(id);
	}
	
	public List<Question> generateExam(NewExamRequest newExamRequest) throws NotEnoughQuestionsException{
		List<Question> exam = new ArrayList<>();
		exam.addAll(generateExamByType(newExamRequest.getTfQuestions(), "TF"));
		exam.addAll(generateExamByType(newExamRequest.getScQuestions(), "SC"));
		exam.addAll(generateExamByType(newExamRequest.getMcQuestions(), "MC"));
		return exam;
	}
	
	private List<Question> generateExamByType(Integer quantity, String typeCode) throws NotEnoughQuestionsException{
		List<Question> generatedQuestions = getAllQuestionByType(typeCode);
		if (generatedQuestions.size() < quantity) {
		 throw new NotEnoughQuestionsException("Not enough true/false questions for request");
		}
		List<Question> exam = new ArrayList<>();
		Random rd = new Random();
		
		for (int i = 0; i < quantity;) {
			int rdQuestion = rd.nextInt(0, generatedQuestions.size());
			Question question = generatedQuestions.get(rdQuestion);
			if (!generatedQuestions.contains(question)) {
				exam.add(question);
				i++;
			}
			
		}
		return exam;
	}

}
