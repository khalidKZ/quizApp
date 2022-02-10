package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.quizapp.Constants as Constants
// تسمح بتحويل التكست فيو الى زر
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition:Int = 1
    private var mQuestionList:ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    private var progressPar:ProgressBar? = null
    private var tvProgressBar:TextView? = null
    private var tvQuestion:TextView? = null
    private var ivImage:ImageView? = null
    private var btnSubmit:Button? = null
    private var userName:String? = null
    private var mCorrectAnswers:Int = 0

    private var optionOne:TextView? = null
    private var optionTwo:TextView? = null
    private var optionThree:TextView? = null
    private var optionFour:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

//حفظت الاسم داخل المتغير USER_NAME الي داخل Constants
   userName = intent.getStringExtra(Constants.USER_NAME)

        progressPar = findViewById(R.id.progressBar)
        tvProgressBar = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_Question)
        ivImage = findViewById(R.id.iv_image)
        btnSubmit = findViewById(R.id.btn_options)

        optionOne = findViewById(R.id.tv_option1)
        optionTwo = findViewById(R.id.tv_option2)
        optionThree = findViewById(R.id.tv_option3)
        optionFour = findViewById(R.id.tv_option4)

        optionOne?.setOnClickListener  (this)
        optionTwo?.setOnClickListener  (this)
        optionThree?.setOnClickListener  (this)
        optionFour?.setOnClickListener  (this)
        btnSubmit?.setOnClickListener  (this)
        mQuestionList = Constants.getQuestion()
        setQuestion()




    }
    private fun setQuestion(){
        defaultOptionView()
        Log.i("questionList size","${mQuestionList!!.size}")

        for (i in mQuestionList!!){
            Log.e("Questions",i.question)
        }


        val question: Question = mQuestionList!![mCurrentPosition - 1]
//        دالة بروقرس  تشير الى خط العداد
        progressPar?.progress = mCurrentPosition
//        currentPosition = 0 progressPar?.max = 9
        tvProgressBar?.text = "$mCurrentPosition" + "/" +  progressPar?.max

        tvQuestion?.text = question.question
        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour
        ivImage?.setImageResource(question.image)
        if (mCurrentPosition == mQuestionList?.size){
            btnSubmit?.text = "Finish"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }
    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        optionOne?.let {
           options.add(0,it)
        }
        optionTwo?.let {
            options.add(1,it)
        }
        optionThree?.let {
            options.add(2,it)
        }
        optionFour?.let {
            options.add(3,it)
        }

      for (option in options){
          option.setTextColor(Color.parseColor("#363A43"))
          option.typeface = Typeface.DEFAULT
          option.background = ContextCompat.getDrawable(
                  this  , R.drawable.default_option_border
          )
      }
    }
//    في حال ظغط المستخدم على احد التكست فيو يتغير شكل التكست فيو ولونه و ايطار حولة
    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionView()
    mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
//    ContextCompat.getDrawable  =
        tv.background = ContextCompat.getDrawable(
                this  , R.drawable.selected_option_porder_bg
        )
    }


// تجعل التكست فيو يمكن الضغط علية
    override fun onClick(view: View?) {
//          view indicate id
       when(view?.id){
           R.id.tv_option1->{
               optionOne?.let {
                   selectedOptionView(it, 1)
               }
       }
           R.id.tv_option2->{
               optionTwo?.let {
                   selectedOptionView(it, 2)
               }

           }
           R.id.tv_option3->{
               optionThree?.let {
                   selectedOptionView(it, 3)
               }

           }
           R.id.tv_option4->{
               optionFour?.let {
                   selectedOptionView(it, 4)
               }

           }

           R.id.btn_options ->{
               if (mSelectedOptionPosition == 0 ){
                   mCurrentPosition++

                   when{
                       mCurrentPosition <= mQuestionList!!.size ->{
                           setQuestion()
                       }
                       else ->{
                           val intent = Intent(this,resultActivity::class.java)
                           intent.putExtra(Constants.USER_NAME,userName)
                           intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                           intent.putExtra(Constants.TOTAL_QUESTION,mQuestionList?.size)
                           startActivity(intent)
                           finish()
                       }

                   }

               }else{
                   val question  = mQuestionList?.get(mCurrentPosition - 1)
//                   في حال الجواب خطأ يتحقق الشرط
                   if (question!!.correctAnswer != mSelectedOptionPosition){
                       answerView(mSelectedOptionPosition, R.drawable.wrong_option_border)
                   }else{
//                       عدد مرات الجواب الصحيح
                       mCorrectAnswers++
                   }
//                   فيحال الجواب صح او خطأ يظهر الجواب الصحيح بالون الاخضر
                   answerView(question.correctAnswer, R.drawable.currect_option_border)
               }
               if (mCurrentPosition == mQuestionList?.size){
                btnSubmit?.text = "FINISH"
               }else{
                   btnSubmit?.text = "GO TO NEXT QUESTION"
               }
               mSelectedOptionPosition = 0

           }
       }
    }

    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1 ->{
                optionOne?.background = ContextCompat.getDrawable(
                        this , drawableView
                )
            }
            2 ->{
                optionTwo?.background = ContextCompat.getDrawable(
                        this , drawableView
                )
            }
            3 ->{
                optionThree?.background = ContextCompat.getDrawable(
                        this , drawableView
                )
            }
            4 ->{
                optionFour?.background = ContextCompat.getDrawable(
                        this , drawableView
                )
            }

        }
    }

}