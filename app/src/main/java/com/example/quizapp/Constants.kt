package com.example.quizapp

object Constants {
    const val USER_NAME: String = "User_name"
    const val TOTAL_QUESTION:String = "Total_question"
    const val  CORRECT_ANSWERS:String="correct_answer"
    fun getQuestion():ArrayList<Question>{

        val questionList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_argentina,
            "Argentina","Australia","denmark","fiji",1
        )
        questionList.add(que1)

        // 2
        val que2 = Question(
            2,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_australia,
            "denmark","Australia","brazil","denmark",2
        )
        questionList.add(que2)

        // 3
        val que3 = Question(
            3,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_belgium,
            "fiji","Australia","denmark","belgium",4
        )
        questionList.add(que3)

        // 4
        val que4 = Question(
            4,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_brazil,
            "india","denmark","brazil","fiji",3
        )
        questionList.add(que4)


        // 5
        val que5 = Question(
            5,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_denmark,
            "denmark","fiji","india","brazil",1
        )
        questionList.add(que5)

        // 6
        val que6 = Question(
            6,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_fiji,
            "Argentina","Australia","brazil","fiji",4
        )
        questionList.add(que6)


        // 7
        val que7 = Question(
            7,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_kuwait,
            "india","kuwait","brazil","fiji",2
        )
        questionList.add(que7)



        // 8
        val que8 = Question(
            8,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_india,
            "kuwait","Australia","brazil","india",4
        )
        questionList.add(que8)

        // 9
        val que9 = Question(
            9,"what Country Does This Flag belong to?",R.drawable.ic_flag_of_new_zealand,
            "NewZealand","Australia","brazil","fiji",1
        )
        questionList.add(que9)


        return questionList
    }
}