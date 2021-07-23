import androidx.room.Update
import com.example.postapp.CreatePostResponse
import com.example.postappapi.PostResponse
import retrofit2.Call
import retrofit2.http.*

//PostApp2
interface Api {
    @GET("posts/achdan")
    fun getPosts(): Call<ArrayList<PostResponse>>

    @POST("post")
    fun insert(@Body post: CreatePostResponse): Call<PostResponse>

    @PUT(  "post/{id}")
    fun update(@Body post: CreatePostResponse): Call<PostResponse>

    @GET("posts/achdan")
    fun getById(@Query ("id") id:String): Call<ArrayList<PostResponse>>


//    @Query("SELECT *FROM PostResponse")
//    fun get(): List<PostResponse>
//



}