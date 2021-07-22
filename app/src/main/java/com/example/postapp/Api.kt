import androidx.room.Insert
import com.example.postapp.CreatePostResponse
import com.example.postappapi.PostResponse
import retrofit2.Call
import retrofit2.http.*

//PostApp2
interface Api {
    @GET("posts/achdan")
    fun getPosts(): Call<ArrayList<PostResponse>>

    @Insert
    fun insert(post: CreatePostResponse): String

//    @Query("SELECT *FROM PostResponse")
//    fun get(): List<PostResponse>
//
//    @Query("SELECT *FROM PostResponse WHERE id = :id")
//    fun getById(id : String): PostResponse


}