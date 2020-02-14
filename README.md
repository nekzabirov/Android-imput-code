# CodeEditText
EditText for code input for example to enter sms code inside box

![SmsCode](https://github.com/nikitaknyzevskiy/CodeEditText/blob/master/screenshot/s1.png?raw=true)

# Install

    	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Module

    	dependencies {
	        implementation 'com.github.nikitaknyzevskiy:Range-seek-bar:1.0'
	}


## Uses

        <com.nikita.codeedittext_view.CodeEditText
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:color="@color/colorPrimary"
      app:textColor="@color/colorPrimary"
      app:maxInput="6"
      app:layout_constraintBottom_toBottomOf="parent"
      app:layout_constraintLeft_toLeftOf="parent"
      app:layout_constraintRight_toRightOf="parent"
      app:layout_constraintTop_toTopOf="parent" />

Kotlin

    codeedittext.onListener = {correct ->
      //Detect input
    }

    //Set code
    codeedittext.text = "123456"

    //Get code
    val code = codeedittext.text

