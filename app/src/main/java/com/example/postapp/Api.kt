import com.example.postappapi.PostResponse
import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @GET("posts/achdan")
    fun getPosts(): Call<ArrayList<PostResponse>>

}