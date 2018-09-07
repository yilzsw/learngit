package com.example.mvp.demo.common.api

import com.google.gson.JsonElement
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * datetime：18-9-6
 * author：yilz
 */
interface ApiService {
    /**
     * GET 请求
     *
     * @param url       api接口url
     * @param parameter 请求参数map
     * @param header    请求头map
     * @return
     */
    @GET
    fun get(@Url url: String, @QueryMap parameter: Map<String, String>, @HeaderMap header: Map<String, String>): Observable<JsonElement>

    /**
     * POST 请求
     *
     * @param url       api接口url
     * @param parameter 请求参数map
     * @param header    请求头map
     * @return
     */
    @FormUrlEncoded
    @POST
    fun post(@Url url: String, @FieldMap parameter: Map<String, String>, @HeaderMap header: Map<String, String>): Observable<JsonElement>

    /**
     * DELETE 请求
     *
     * @param url       api接口url
     * @param parameter 请求参数map
     * @param header    请求头map
     * @return
     */
    @FormUrlEncoded
    @DELETE
    fun delete(@Url url: String, @FieldMap parameter: Map<String, String>, @HeaderMap header: Map<String, String>): Observable<JsonElement>


    /**
     * PUT 请求
     *
     * @param url       api接口url
     * @param parameter 请求参数map
     * @param header    请求头map
     * @return
     */
    @FormUrlEncoded
    @PUT
    fun put(@Url url: String, @FieldMap parameter: Map<String, String>, @HeaderMap header: Map<String, String>): Observable<JsonElement>

    /**
     * 多文件上传
     *
     * @param url       api接口url
     * @param parameter 请求接口参数
     * @param header    请求头map
     * @param fileList  文件列表
     * @return
     * @Multipart 文件上传注解
     */
    @Multipart
    @POST
    fun upload(@Url url: String, @PartMap parameter: Map<String, String>, @HeaderMap header: Map<String, String>, @Part fileList: List<MultipartBody.Part>): Observable<JsonElement>


    /**
     * 断点续传下载
     *
     * @param range 断点下载范围 bytes= start - end
     * @param url   下载地址
     * @return
     * @Streaming 防止内容写入内存, 大文件通过此注解避免OOM
     */
    @Streaming
    @GET
    fun download(@Header("RANGE") range: String, @Url url: String): Observable<ResponseBody>

}