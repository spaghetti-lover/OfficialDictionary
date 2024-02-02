package quiz;

public class Question {
    private int id;
    private String questionText;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String answer;

    public Question(){

    }
    public Question(String questionText, String opt1, String opt2, String opt3, String opt4, String answer){
        this.questionText = questionText;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.answer = answer;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setQuestionText(String questionText){
        this.questionText = questionText;
    }
    public String getQuestionText(){
        return questionText;
    }
    public void setOption1(String opt1){
        this.opt1 = opt1;
    }
    public String getOption1(){
        return opt1;
    }
    public void setOption2(String opt2){
        this.opt2 = opt2;
    }
    public String getOption2(){
        return opt2;
    }
    public void setOption3(String opt3){
        this.opt3 = opt3;
    }
    public String getOption3(){
        return opt3;
    }
    public void setOption4(String opt4){
        this.opt4 = opt4;
    }
    public String getOption4(){
        return opt4;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public String getAnswer(){
        return answer;
    }

}
