package com.example.txtfilecreated

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class MainActivity : AppCompatActivity() {
    lateinit var edtText: EditText
    lateinit var btnSave: Button
    lateinit var btnLoad: Button
    lateinit var btnDelete: Button

    val FILE_NAME="example.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtText=findViewById(R.id.edt_text)
        btnSave=findViewById(R.id.btnSave)
        btnLoad=findViewById(R.id.btnLoad)
        btnDelete=findViewById(R.id.btnDelete)



        btnLoad.setOnClickListener {
            val fis:FileInputStream=openFileInput(FILE_NAME)
            val isr: InputStreamReader= InputStreamReader(fis)
            val br: BufferedReader= BufferedReader(isr)
            var sb:String=""
            var text=""
            while ((text==br.readLine())!=null)
            {
                //sb.append(text).append("\n")
                sb=sb+""+text+"\n"
            }
            edtText.setText(sb.toString())

            if(fis!=null)
            {
                fis.close()
            }

        }

        btnSave.setOnClickListener {

           /* val folder = filesDir
            val f = File(folder, "folder_name")
            f.mkdir()*/
            val text: String=edtText.text.toString()
            val fos: FileOutputStream=openFileOutput(FILE_NAME, MODE_PRIVATE)
            fos.write(text.toByteArray(Charsets.UTF_8))
            edtText.text.clear()
            Toast.makeText(this, "Saved to " + filesDir + "/" + FILE_NAME, Toast.LENGTH_SHORT).show()

            if(fos!=null)
            {
                fos.close()
            }
        }


        btnDelete.setOnClickListener {

            val dir: File = filesDir
            val file = File(dir, FILE_NAME)
            val deleted: Boolean = file.delete()

            Toast.makeText(this,"Deleted ="+deleted,Toast.LENGTH_SHORT).show()
            if(deleted)
            {
                Toast.makeText(this,"Delete file successFully",Toast.LENGTH_SHORT)
            }
            else
            {
                Toast.makeText(this,"No Delete ",Toast.LENGTH_SHORT).show()
            }
          /*  if(dir.isDirectory)
            {
                //Toast.makeText(this,"Directory is Availaible",Toast.LENGTH_SHORT).show()
            }
            else
            {
                //Toast.makeText(this,"Directory is Not Avaialiable",Toast.LENGTH_SHORT).show()
            }*/



        }

    }






}