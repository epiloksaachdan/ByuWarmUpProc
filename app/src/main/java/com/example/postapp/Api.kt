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

//    @Query("SELECT *FROM PostResponse")
//    fun get(): List<PostResponse>
//
//    @Query("SELECT *FROM PostResponse WHERE id = :id")
//    fun getById(id : String): PostResponse


}