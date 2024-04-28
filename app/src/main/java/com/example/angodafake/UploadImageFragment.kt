package com.example.angodafake

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.angodafake.db.Picture_Hotel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.storage.storage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UploadImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UploadImageFragment(private var idUser: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var btn_upload: Button
    private lateinit var uploadImage: ImageView
    private lateinit var progressBar: ProgressBar
    private var imageUri: Uri? = null

    private var databaseRef = Firebase.database.reference.child("hotel_pictures/1")
    private val storageRef = Firebase.storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_upload_image, container, false)
        initUI(view)

        progressBar.visibility = View.INVISIBLE

        val activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
                if (it.resultCode == Activity.RESULT_OK){
                    val data = it.data
                    imageUri = data?.data
                    uploadImage.setImageURI(imageUri)
                } else{
                    Toast.makeText(requireContext(), "No Image Selected", Toast.LENGTH_SHORT).show()
                }
        }

        uploadImage.setOnClickListener {
            val photoPicker = Intent()
            photoPicker.setAction(Intent.ACTION_GET_CONTENT)
            photoPicker.setType("image/*")
            activityResultLauncher.launch(photoPicker)
        }

        btn_upload.setOnClickListener {
            if (imageUri != null){
                uploadToFirebase(imageUri!!)
            } else{
                Toast.makeText(requireContext(), "Please select image", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun uploadToFirebase(imageUri: Uri) {
        val imgRef = storageRef.child("${System.currentTimeMillis()}.${getFileExtension(imageUri)}")
        imgRef.putFile(imageUri)
            .addOnSuccessListener {
                imgRef.downloadUrl.addOnSuccessListener {
                    val picture = Picture_Hotel(null, null, it.toString())
                    val key = databaseRef.push().key
                    if (key != null){
                        databaseRef.child(key).setValue(picture)
                        progressBar.visibility = View.INVISIBLE
                        val mainActivity = requireActivity() as MainActivity
                        mainActivity.replaceFragment(addHotelImageFragment(idUser))
                    } else{
                        Toast.makeText(requireContext(), "Counldn't get push key for hotel_pictures", Toast.LENGTH_SHORT).show()
                    }

                }
            }.addOnProgressListener {
                progressBar.visibility = View.VISIBLE
            }.addOnFailureListener {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getFileExtension(imageUri: Uri): String {
        val contentResolver = requireContext().contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(contentResolver.getType(imageUri))!!
    }

    private fun initUI(view: View){
        btn_upload = view.findViewById(R.id.btn_upload)
        uploadImage = view.findViewById(R.id.uploadImage)
        progressBar = view.findViewById(R.id.progressBar)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UploadImageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, idUser: String) =
            UploadImageFragment(idUser).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}