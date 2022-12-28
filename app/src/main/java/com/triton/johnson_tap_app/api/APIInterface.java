package com.triton.johnson_tap_app.api;

import com.triton.johnson_tap_app.requestpojo.ACKService_SubmitRequest;
import com.triton.johnson_tap_app.requestpojo.Agent_new_screenRequest;
import com.triton.johnson_tap_app.requestpojo.AuditRequest;
import com.triton.johnson_tap_app.requestpojo.Change_PasswordRequest;
import com.triton.johnson_tap_app.GetFieldListResponse;
import com.triton.johnson_tap_app.requestpojo.CheckOutstandingJobRequest;
import com.triton.johnson_tap_app.requestpojo.Check_Pod_StatusRequest;
import com.triton.johnson_tap_app.requestpojo.FbTokenRequest;
import com.triton.johnson_tap_app.requestpojo.Fetch_MrList_Request;
import com.triton.johnson_tap_app.requestpojo.GetCustomer_Engineer_Request;
import com.triton.johnson_tap_app.requestpojo.GetFieldListRequest;
import com.triton.johnson_tap_app.requestpojo.Joblist_new_screenRequest;
import com.triton.johnson_tap_app.requestpojo.LRService_SubmitRequest;
import com.triton.johnson_tap_app.requestpojo.NotificationListRequest;
import com.triton.johnson_tap_app.requestpojo.Preventive_ChecklistRequest;
import com.triton.johnson_tap_app.requestpojo.Preventive_Submit_Request;
import com.triton.johnson_tap_app.requestpojo.ServiceUserdetailsRequest;
import com.triton.johnson_tap_app.requestpojo.ServiceUserdetailsRequestResponse;
import com.triton.johnson_tap_app.requestpojo.BreedTypeRequest1;
import com.triton.johnson_tap_app.requestpojo.SkipJobDetailRequest;
import com.triton.johnson_tap_app.requestpojo.UpdateOutstandingJobRequest;
import com.triton.johnson_tap_app.responsepojo.Agent_new_screenResponse;
import com.triton.johnson_tap_app.responsepojo.Agent_pop_statusResponse;
import com.triton.johnson_tap_app.responsepojo.Auditcheckresponse;
import com.triton.johnson_tap_app.responsepojo.BreedTypeResponse1;
import com.triton.johnson_tap_app.responsepojo.Change_PasswordResponse;
import com.triton.johnson_tap_app.responsepojo.CheckOutstandingJobResponse;
import com.triton.johnson_tap_app.responsepojo.Check_Pod_StatusResponse;
import com.triton.johnson_tap_app.responsepojo.CustomerDetails_EngineerResponse;
import com.triton.johnson_tap_app.responsepojo.Fetch_MrList_Response;
import com.triton.johnson_tap_app.responsepojo.FormDataStoreResponse;
import com.triton.johnson_tap_app.responsepojo.GetFetchLatestVersionResponse;
import com.triton.johnson_tap_app.requestpojo.JobFindRequest;
import com.triton.johnson_tap_app.responsepojo.GetPopUpImageRequest;
import com.triton.johnson_tap_app.responsepojo.GetPopupImageResponse;
import com.triton.johnson_tap_app.responsepojo.Joblist_new_screenResponse;
import com.triton.johnson_tap_app.responsepojo.JobnoFindResponse;
import com.triton.johnson_tap_app.responsepojo.LR_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.MR_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.Material_DetailsResponseACK;
import com.triton.johnson_tap_app.responsepojo.NotificationListResponse;
import com.triton.johnson_tap_app.responsepojo.Preventive_ChecklistResponse;
import com.triton.johnson_tap_app.responsepojo.RTGS_PopResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveLocalValueBRResponse;
import com.triton.johnson_tap_app.responsepojo.RetriveResponsePR;
import com.triton.johnson_tap_app.responsepojo.Retrive_LocalValueResponse;
import com.triton.johnson_tap_app.responsepojo.ServiceUserdetailsResponse;
import com.triton.johnson_tap_app.responsepojo.Service_list_new_screenResponse;
import com.triton.johnson_tap_app.responsepojo.SubmitDailyResponse;
import com.triton.johnson_tap_app.responsepojo.SubmitPreventiveResponse;
import com.triton.johnson_tap_app.responsepojo.SuccessResponse;
import com.triton.johnson_tap_app.responsepojo.UpdatePopImageRequest;
import com.triton.johnson_tap_app.responsepojo.UserTypeListResponse;
import com.triton.johnson_tap_app.data.form3submit.Form3SubmitIP;
import com.triton.johnson_tap_app.requestpojo.ActivityGetListNumberRequest;
import com.triton.johnson_tap_app.requestpojo.AgentRequest;
import com.triton.johnson_tap_app.requestpojo.BD_DetailsRequest;
import com.triton.johnson_tap_app.requestpojo.Breakdowm_Submit_Request;
import com.triton.johnson_tap_app.requestpojo.Breakdown_submitRequest;
import com.triton.johnson_tap_app.requestpojo.CheckAttenRequest;
import com.triton.johnson_tap_app.requestpojo.CountRequest;
import com.triton.johnson_tap_app.requestpojo.Count_pasusedRequest;
import com.triton.johnson_tap_app.requestpojo.CreateRequest;
import com.triton.johnson_tap_app.requestpojo.Custom_detailsRequest;
import com.triton.johnson_tap_app.requestpojo.Custom_nameRequest;
import com.triton.johnson_tap_app.requestpojo.Feedback_DetailsRequest;
import com.triton.johnson_tap_app.requestpojo.Feedback_GroupRequest;
import com.triton.johnson_tap_app.requestpojo.JobListRequest;
import com.triton.johnson_tap_app.requestpojo.Job_Details_TextRequest;
import com.triton.johnson_tap_app.requestpojo.Job_statusRequest;
import com.triton.johnson_tap_app.requestpojo.Job_status_updateRequest;
import com.triton.johnson_tap_app.requestpojo.LoginRequest1;
import com.triton.johnson_tap_app.requestpojo.LogoutRequest;
import com.triton.johnson_tap_app.requestpojo.Pasused_ListRequest;
import com.triton.johnson_tap_app.requestpojo.ServiceRequest;
import com.triton.johnson_tap_app.requestpojo.ViewStatusRequest;
import com.triton.johnson_tap_app.responsepojo.ActivityGetListNumberResponse;
import com.triton.johnson_tap_app.responsepojo.AgentResponse;
import com.triton.johnson_tap_app.responsepojo.BD_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.Breakdown_submitrResponse;
import com.triton.johnson_tap_app.responsepojo.CheckAttenResponse;
import com.triton.johnson_tap_app.responsepojo.CountResponse;
import com.triton.johnson_tap_app.responsepojo.Count_pasusedResponse;
import com.triton.johnson_tap_app.responsepojo.CreateResponse;
import com.triton.johnson_tap_app.responsepojo.Custom_detailsResponse;
import com.triton.johnson_tap_app.responsepojo.Custom_nameResponse;
import com.triton.johnson_tap_app.responsepojo.Feedback_DetailsResponse;
import com.triton.johnson_tap_app.responsepojo.Feedback_GroupResponse;
import com.triton.johnson_tap_app.responsepojo.FileUploadResponse;
import com.triton.johnson_tap_app.responsepojo.JobListResponse;
import com.triton.johnson_tap_app.responsepojo.Job_Details_TextResponse;
import com.triton.johnson_tap_app.responsepojo.Job_statusResponse;
import com.triton.johnson_tap_app.responsepojo.Job_status_updateResponse;
import com.triton.johnson_tap_app.responsepojo.LoginResponse1;
import com.triton.johnson_tap_app.responsepojo.LogoutResponse;
import com.triton.johnson_tap_app.responsepojo.Pasused_ListResponse;
import com.triton.johnson_tap_app.responsepojo.ServiceResponse;
import com.triton.johnson_tap_app.responsepojo.SubmitBreakdownResponseee;
import com.triton.johnson_tap_app.responsepojo.ViewStatusResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface APIInterface {

    @GET("activity/tab_getlatest_version")
    Call<GetFetchLatestVersionResponse> getlatestversionrequestcall();

//    /*login*/
//    @POST("user_management/mobile/login_page")
//    Call<LoginResponse> LoginResponseCall(@Header("Content-Type") String type, @Body LoginRequest loginRequest);

    /*login*/
//    @POST("tab_usermanager/mobile/login_page")
//    Call<LoginResponse> LoginResponseCall(@Header("Content-Type") String type, @Body LoginRequest loginRequest);

    @POST("service_userdetails/mobile/login_page")
    Call<LoginResponse1> LoginResponseCall(@Header("Content-Type") String type, @Body LoginRequest1 loginRequest);

    @POST("service_userdetails/change_password")
    Call<Change_PasswordResponse> Change_PasswordResponseCall(@Header("Content-Type") String type, @Body Change_PasswordRequest loginRequest);

    @POST("service_userdetails/info")
    Call<AgentResponse> AgentResponseCall(@Header("Content-Type") String type, @Body AgentRequest agentRequest);

    @POST("service_attendance/logout")
    Call<LogoutResponse> logoutResponseCall(@Header("Content-Type") String type, @Body LogoutRequest logoutRequest);

    @POST("service_attendance/check_login_status")
    Call<CheckAttenResponse> CheckAttenResponseCall(@Header("Content-Type") String type, @Body CheckAttenRequest checkattenRequest);

    @POST("service_attendance/create")
    Call<CreateResponse> CreateResponseCall(@Header("Content-Type") String type, @Body CreateRequest createRequest);

    @POST("service_userdetails/mainmenu_counts")
    Call<CountResponse> CountResponseCall(@Header("Content-Type") String type, @Body CountRequest countRequest);

    @POST("service_userdetails/job_status_count")
    Call<Count_pasusedResponse> Count_pasusedResponseCall(@Header("Content-Type") String type, @Body Count_pasusedRequest countRequest);

    @POST("service_userdetails/customer_details")
    Call<Custom_detailsResponse> Custom_detailsResponseCall(@Header("Content-Type") String type, @Body Custom_detailsRequest custom_detailsRequest);

    @POST("service_userdetails/job_details_in_text")
    Call<Job_Details_TextResponse> Job_Details_TextResponseCall(@Header("Content-Type") String type, @Body Job_Details_TextRequest custom_detailsRequest);

    @POST("service_userdetails/check_work_status")
    Call<Job_statusResponse> job_statusResponseCall(@Header("Content-Type") String type, @Body Job_statusRequest custom_detailsRequest);

    @POST("service_userdetails/job_work_status_update")
    Call<Job_status_updateResponse> job_status_updateResponseCall(@Header("Content-Type") String type, @Body Job_status_updateRequest custom_detailsRequest);

    @POST("breakdown_data_management/mr_job_work_status_update")
    Call<Job_status_updateResponse> BreakdownMrJobWorkStatusResponseCall(@Header("Content-Type") String type, @Body Job_status_updateRequest custom_detailsRequest);

    @POST("service_userdetails/service_list")
    Call<ServiceResponse> ServiceResponseCall(@Header("Content-Type") String type, @Body ServiceRequest serviceRequest);

    @POST("service_userdetails/pause_job_list")
    Call<Pasused_ListResponse> Pasused_listResponseCall(@Header("Content-Type") String type, @Body Pasused_ListRequest pasusedRequest);

    @POST("service_userdetails/fetch_view_status")
    Call<ViewStatusResponse> View_statusResponseCall(@Header("Content-Type") String type, @Body ViewStatusRequest viewRequest);

    @POST("service_userdetails/new_job_list")
    Call<JobListResponse> JobListResponseCall(@Header("Content-Type") String type, @Body JobListRequest joblistRequest);
    //    /*Email OTP */

    @POST("activity/form3_rtgs_jobno_find")
    Call<JobnoFindResponse> JobnoFindResponseCall(@Header("Content-Type") String type, @Body JobFindRequest emailOTPRequest);

    @POST("activity/form3_rtgs_jobno_find_customer")
    Call<Custom_nameResponse> Custom_nameResponseCall(@Header("Content-Type") String type, @Body Custom_nameRequest emailOTPRequest);

    @POST("service_userdetails/bd_details_list")
    Call<BD_DetailsResponse> BD_DetailsResponseCall(@Header("Content-Type") String type, @Body BD_DetailsRequest serviceRequest);

    @POST("service_userdetails/feedback_details_list")
    Call<Feedback_DetailsResponse> Feedback_DetailsResponseCall(@Header("Content-Type") String type, @Body Feedback_DetailsRequest serviceRequest);

    @POST("service_userdetails/feedback_group_list")
    Call<Feedback_GroupResponse> feedback_groupResponseCall(@Header("Content-Type") String type, @Body Feedback_GroupRequest serviceRequest);

    @POST("service_userdetails/breakdown_data_submit")
    Call<Breakdown_submitrResponse> BreakResponseCall(@Header("Content-Type") String type, @Body Breakdown_submitRequest serviceRequest);

    @POST("service_temp_data/create_local_value_form_1")
    Call<SuccessResponse> createLocalvalueBD(@Header("Content-Type") String type, @Body Breakdowm_Submit_Request breakdowmSubmitRequest);

    @POST("service_temp_data/retrive_local_value_form_1")
    Call<RetriveLocalValueBRResponse> retriveLocalValueBRCall(@Header("Content-Type") String type, @Body Job_status_updateRequest job_status_updateRequest);

    /*Image upload*/
    @Multipart
    @POST("upload2")
    Call<FileUploadResponse> getImageStroeResponse(@Part MultipartBody.Part file);

    @POST("activity/get_joins_user_list")
    Call<BreedTypeResponse1> breedTypeResponseByPetIdCall(@Header("Content-Type") String type, @Body BreedTypeRequest1 breedTypeRequest);

    @POST("activity/form3_submit")
    Call<SubmitDailyResponse> locationAddResponseCall(@Header("Content-Type") String type, @Body Form3SubmitIP locationAddRequest);

    @POST("service_userdetails/breakdown_data_submit_test_one")
    Call<SubmitBreakdownResponseee> submitAddResponseCall(@Header("Content-Type") String type, @Body Breakdowm_Submit_Request locationAddRequest);

    @POST("activity/getlist_number")
    Call<ActivityGetListNumberResponse> activityGetListNumberResponseCall(@Header("Content-Type") String type, @Body ActivityGetListNumberRequest activityGetListNumberRequest);

    @GET("activity/form3_rtgs_list")
    Call<RTGS_PopResponse> filterPageInfoResponseCall(@Header("Content-Type") String type);

    @GET("usertype/mobile/getlist")
    Call<UserTypeListResponse> userTypeListResponseCall(@Header("Content-Type") String type);

//  2nd Form Services   //

    @POST("preventive_service_data_management/job_status_count")
    Call<Count_pasusedResponse> Count_pasused_secondResponseCall(@Header("Content-Type") String type, @Body Count_pasusedRequest countRequest);

    @POST("preventive_service_data_management/new_job_list")
    Call<JobListResponse> JobList_PreventiveResponseCall(@Header("Content-Type") String type, @Body JobListRequest joblistRequest);

    @POST("preventive_service_data_management/pause_job_list_pm")
    Call<Pasused_ListResponse> Pasused_listBMRResponseCall(@Header("Content-Type") String type, @Body Pasused_ListRequest pasusedRequest);

    @POST("preventive_service_data_management/customer_details")
    Call<Custom_detailsResponse> Custom_details_PreventiveResponseCall(@Header("Content-Type") String type, @Body Custom_detailsRequest custom_detailsRequest);

    @POST("preventive_service_data_management/check_work_status")
    Call<Job_statusResponse> job_status_PreventiveResponseCall(@Header("Content-Type") String type, @Body Job_statusRequest custom_detailsRequest);

    @POST("preventive_service_data_management/job_work_status_update")
    Call<Job_status_updateResponse> job_status_update_PreventiveResponseCall(@Header("Content-Type") String type, @Body Job_status_updateRequest custom_detailsRequest);

    @POST("preventive_service_data_management/preventive_checklist")
    Call<Preventive_ChecklistResponse> Preventive_ChecklistResponseCall(@Header("Content-Type") String type, @Body Preventive_ChecklistRequest serviceRequest);

    @POST("preventive_service_data_management/check_pod_status")
    Call<Check_Pod_StatusResponse> Check_Pod_StatusResponseCall(@Header("Content-Type") String type, @Body Check_Pod_StatusRequest countRequest);

    @POST("preventive_service_data_management/check_list_value")
    Call<GetFieldListResponse> getfieldList_preResponseCall(@Header("Content-Type") String type, @Body GetFieldListRequest getFieldListRequest);

    @POST("preventive_service_data_management/submit_data")
    Call<SubmitPreventiveResponse> submitAddPreResponseCall(@Header("Content-Type") String type, @Body Preventive_Submit_Request locationAddRequest);

    @POST("preventive_service_data_management/submit_data")
    Call<FormDataStoreResponse> getformdataListResponseCall1(@Header("Content-Type") String type, @Body GetFieldListResponse getFieldListResponse);

    @POST("service_temp_data/create_local_value_form_2")
    Call<SuccessResponse> createLocalValueCallPM(@Header("Content-Type") String type, @Body Preventive_Submit_Request preventiveSubmitRequest);

    @POST("service_temp_data/retrive_local_value_form_2")
    Call<RetriveResponsePR> retriveLocalValuePRCall(@Header("Content-Type") String type, @Body Job_status_updateRequest job_status_updateRequest);

    @POST("preventive_service_data_management/check_list_value_temp")
    Call<GetFieldListResponse> checkLocalValuecall(@Header("Content-Type") String type, @Body GetFieldListRequest getFieldListRequest);

    @POST("preventive_data_management/job_details_in_text")
    Call<Job_Details_TextResponse> Job_DetailsTextPreventiveResponseCall(@Header("Content-Type") String type, @Body Job_Details_TextRequest custom_detailsRequest);


    @POST("service_temp_data/create_local_value_form_check")
    Call<SuccessResponse> createLocalValueformcheckPM(@Header("Content-Type") String type, @Body Preventive_Submit_Request preventiveSubmitRequest);

    //  3 Form   ///

    @POST("breakdown_data_management/service_mr_job_status_count")
    Call<Count_pasusedResponse> Count_JobstatuscountCall(@Header("Content-Type") String type, @Body Count_pasusedRequest countRequest);

    @POST("breakdown_data_management/service_mr_customer_details")
    Call<Custom_detailsResponse> Customer_detailsResponseCall(@Header("Content-Type") String type, @Body Custom_detailsRequest custom_detailsRequest);

    @POST("breakdown_data_management/service_mr_check_work_status")
    Call<Job_statusResponse> CheckworkStatusCall(@Header("Content-Type") String type, @Body Job_statusRequest custom_detailsRequest);

    @POST("breakdown_data_management/pause_job_list")
    Call<Pasused_ListResponse> Pasused_listResponseBMRCall(@Header("Content-Type") String type, @Body Pasused_ListRequest pasusedRequest);

    @POST("breakdown_data_management/service_mr_eng_mrlist")
    Call<ServiceUserdetailsResponse> ServiceUserdetailsResponseMRlistCall(@Header("Content-Type") String type, @Body ServiceUserdetailsRequest serviceUserdetailsRequest);

    @POST("breakdown_data_management/service_mr_eng_mrlist_submit")
    Call<SuccessResponse> ServiceUserdetailsRequestsubmitMRListCall(@Header("Content-Type") String type, @Body ServiceUserdetailsRequestResponse serviceUserdetailsRequestResponse);

    @POST("breakdown_data_management/service_mr_new_job_list")
    Call<JobListResponse> NewJobListCall(@Header("Content-Type") String type, @Body JobListRequest joblistRequest);

    @POST("breakdown_data_management/fetch_mr_list")
    Call<Fetch_MrList_Response> FetchMrListBreakdownMRCall(@Header("Content-Type") String type, @Body Fetch_MrList_Request fetchMrListRequest);

    @POST("service_temp_data/create_local_value")
    Call<SuccessResponse> createLocalValueCallBMR(@Header("Content-Type") String type, @Body ServiceUserdetailsRequestResponse serviceUserdetailsRequestResponse);

    @POST("service_temp_data/retrive_local_value")
    Call<Retrive_LocalValueResponse> retriveLocalValueCall(@Header("Content-Type") String type, @Body Job_status_updateRequest job_status_updateRequest);

    // 4 Form /////

    @POST("preventive_service_data_management/service_mr_job_status_count")
    Call<Count_pasusedResponse> Count_JobstatuscountPrventiveMRCall(@Header("Content-Type") String type, @Body Count_pasusedRequest countRequest);

    @POST("preventive_service_data_management/service_mr_new_job_list")
    Call<JobListResponse> NewJobListPrventiveMRCall(@Header("Content-Type") String type, @Body JobListRequest joblistRequest);

    @POST("preventive_service_data_management/pause_job_list")
    Call<Pasused_ListResponse> Pasused_listResponsePMRCall(@Header("Content-Type") String type, @Body Pasused_ListRequest pasusedRequest);

    @POST("preventive_service_data_management/service_mr_customer_details")
    Call<Custom_detailsResponse> Customer_detailsResponsePrventiveMRCall(@Header("Content-Type") String type, @Body Custom_detailsRequest custom_detailsRequest);

    @POST("preventive_service_data_management/service_mr_check_work_status")
    Call<Job_statusResponse> CheckworkStatusPrventiveMRCall(@Header("Content-Type") String type, @Body Job_statusRequest custom_detailsRequest);


    @POST("preventive_service_data_management/service_mr_eng_mrlist")
    Call<ServiceUserdetailsResponse> ServiceUserdetailsPrventiveMRCall(@Header("Content-Type") String type, @Body ServiceUserdetailsRequest serviceUserdetailsRequest);


    @POST("preventive_service_data_management/mr_job_work_status_update")
    Call<Job_status_updateResponse> PreventiveMrJobWorkStatusResponseCall(@Header("Content-Type") String type, @Body Job_status_updateRequest custom_detailsRequest);

    @POST("preventive_service_data_management/service_mr_eng_mrlist_submit")
    Call<SuccessResponse> ServiceUserdetailsRequestsubmitPrventiveMRCall(@Header("Content-Type") String type, @Body ServiceUserdetailsRequestResponse serviceUserdetailsRequestResponse);

    @POST("preventive_service_data_management/local_service_mr_eng_mrlist_submit")
    Call<SuccessResponse> LocalServiceUserdetailsRequestsubmitPrventiveMRCall(@Header("Content-Type") String type, @Body ServiceUserdetailsRequestResponse serviceUserdetailsRequestResponse);

    @POST("service_temp_data/create_local_value_form_4")
    Call<SuccessResponse> createLocalValueCallPMR(@Header("Content-Type") String type, @Body ServiceUserdetailsRequestResponse serviceUserdetailsRequestResponse);

    @POST("service_temp_data/retrive_local_value_form_4")
    Call<Retrive_LocalValueResponse> retriveLocalValueCallPMR(@Header("Content-Type") String type, @Body Job_status_updateRequest job_status_updateRequest);


   // Form 5

    @POST("lr_service_data_management/service_lr_job_status_count")
    Call<JobListResponse> NewJobLRListCall(@Header("Content-Type") String type, @Body JobListRequest joblistRequest);

    @POST("lr_service_data_management/service_lr_job_status_count")
    Call<Count_pasusedResponse> Count_JobstatuscountLRCall(@Header("Content-Type") String type, @Body Count_pasusedRequest countRequest);

    @POST("lr_service_data_management/service_lr_new_job_list")
    Call<JobListResponse> NewJobListLRCall(@Header("Content-Type") String type, @Body JobListRequest joblistRequest);

    @POST("lr_service_data_management/pause_job_list")
    Call<Pasused_ListResponse> Pasused_listResponseLRCall(@Header("Content-Type") String type, @Body Pasused_ListRequest pasusedRequest);

    @POST("lr_service_data_management/service_lr_customer_details")
    Call<LR_DetailsResponse> LRDetailsResponseCall(@Header("Content-Type") String type, @Body Custom_detailsRequest custom_detailsRequest);

    @POST("lr_service_data_management/service_mr_check_work_status")
    Call<Job_statusResponse> CheckworkStatusLRCall(@Header("Content-Type") String type, @Body Job_statusRequest custom_detailsRequest);

    @POST("lr_service_data_management/lr_job_details_in_text")
    Call<Job_Details_TextResponse> jobdetailsLR(@Header("Content-Type") String type, @Body Job_Details_TextRequest custom_detailsRequest);

    @POST("lr_service_data_management/lr_job_work_status_update")
    Call<Job_status_updateResponse> job_status_updateLRResponseCall(@Header("Content-Type") String type, @Body Job_status_updateRequest custom_detailsRequest);

    @POST("lr_service_data_management/lr_submit_data")
    Call<SuccessResponse> submitAddLRResponseCall(@Header("Content-Type") String type, @Body LRService_SubmitRequest lrService_submitRequest);


    @POST("service_temp_data/create_local_value_form_5")
    Call<SuccessResponse> createLocalValueCallLR(@Header("Content-Type") String type, @Body LRService_SubmitRequest lrService_submitRequest);

    @POST("service_temp_data/retrive_local_value_form_5")
    Call<Retrive_LocalValueResponse> retriveLocalValueCallLR(@Header("Content-Type") String type, @Body Job_status_updateRequest job_status_updateRequest);

    // Form 6

    @POST("part_replacement/service_prtrpmt_job_status_count")
    Call<Count_pasusedResponse> Count_JobstatuscountACKCall(@Header("Content-Type") String type, @Body Count_pasusedRequest countRequest);

    @POST("part_replacement/service_prtrpmt_new_job_list")
    Call<JobListResponse> NewJobListACKCall(@Header("Content-Type") String type, @Body JobListRequest joblistRequest);

    @POST("part_replacement/pause_job_list")
    Call<Pasused_ListResponse> Pasused_listACKCall(@Header("Content-Type") String type, @Body Pasused_ListRequest pasusedRequest);

    @POST("part_replacement/service_prtrpmt_customer_details")
    Call<MR_DetailsResponse> MRDetailsResponseCall(@Header("Content-Type") String type, @Body Custom_detailsRequest custom_detailsRequest);

    @POST("part_replacement/service_prtrpmt_marterial_list")
    Call<Material_DetailsResponseACK> MaterialResponseCall(@Header("Content-Type") String type, @Body Custom_detailsRequest custom_detailsRequest);

    @POST("part_replacement/service_mr_check_work_status")
    Call<Job_statusResponse> CheckworkStatusACKCall(@Header("Content-Type") String type, @Body Job_statusRequest custom_detailsRequest);

    @POST("part_replacement/prtrpmt_job_work_status_update")
    Call<Job_status_updateResponse> job_status_updateACKResponseCall(@Header("Content-Type") String type, @Body Job_status_updateRequest custom_detailsRequest);

    @POST("part_replacement/prtrpmt_job_details_in_text")
    Call<Job_Details_TextResponse> jobdetailsACK(@Header("Content-Type") String type, @Body Job_Details_TextRequest custom_detailsRequest);

    @POST("part_replacement/prtrpmt_submit_data")
    Call<SuccessResponse> submitAddACKResponseCall(@Header("Content-Type") String type, @Body ACKService_SubmitRequest lrService_submitRequest);

    @POST("service_temp_data/create_local_value_form_6")
    Call<SuccessResponse> createLocalValueCallACK(@Header("Content-Type") String type, @Body ACKService_SubmitRequest lrService_submitRequest);

    @POST("service_temp_data/retrive_local_value_form_6")
    Call<Retrive_LocalValueResponse> retriveLocalValueCallACK(@Header("Content-Type") String type, @Body Job_status_updateRequest job_status_updateRequest);


    /// Form 7

    @POST("audit_data_management/service_audit_job_status_count")
    Call<Count_pasusedResponse> Count_AuditstatuscountCall(@Header("Content-Type") String type, @Body Count_pasusedRequest countRequest);

    @POST("audit_data_management/service_audit_new_job_list")
    Call<JobListResponse> NewJobAuditListCall(@Header("Content-Type") String type, @Body JobListRequest joblistRequest);

    @POST("audit_data_management/service_audit_check_work_status")
    Call<Job_statusResponse> CheckworkAuditStatusCall(@Header("Content-Type") String type, @Body Job_statusRequest custom_detailsRequest);

    @POST("audit_data_management/audit_job_work_status_update")
    Call<Job_status_updateResponse> job_status_updateAuditResponseCall(@Header("Content-Type") String type, @Body Job_status_updateRequest custom_detailsRequest);

    @POST("audit_data_management/audit_check_list_value")
    Call<GetFieldListResponse> Preventive_ChecklistAuditResponseCall(@Header("Content-Type") String type, @Body GetFieldListRequest getFieldListRequest);

    @POST("audit_data_management/fetch_mr_list")
    Call<Fetch_MrList_Response> FetchMrListAuditCall(@Header("Content-Type") String type, @Body Fetch_MrList_Request fetchMrListRequest);

    @POST("audit_data_management/submit")
    Call<SuccessResponse> AuditSubmiCall(@Header("Content-Type") String type, @Body AuditRequest auditRequest);

    @POST("service_temp_data/create_local_value_form_7")
    Call<SuccessResponse> createLocalValueCallAudit(@Header("Content-Type") String type, @Body AuditRequest auditRequest);

    @POST("service_temp_data/create_local_value_form_check_two")
    Call<SuccessResponse> createLocalValueformcheckAudit(@Header("Content-Type") String type, @Body AuditRequest auditRequest);
    ///////

    @POST("service_admin/getlist_userlist")
    Call<Agent_new_screenResponse> Agent_new_screenResponseCall(@Header("Content-Type") String type, @Body Agent_new_screenRequest serviceRequest);

    @POST("service_admin/fetch_single_userstatus")
    Call<Agent_pop_statusResponse> Agent_pop_statusResponseCall(@Header("Content-Type") String type, @Body Agent_new_screenRequest custom_detailsRequest);

    @POST("service_admin/service_list")
    Call<Service_list_new_screenResponse> Services_list_new_screenResponseCall(@Header("Content-Type") String type, @Body Agent_new_screenRequest serviceRequest);

    @POST("service_admin/job_list")
    Call<Joblist_new_screenResponse> Job_list_new_screenResponseCall(@Header("Content-Type") String type, @Body Joblist_new_screenRequest serviceRequest);

    @POST("service_admin/customer_details")
    Call<CustomerDetails_EngineerResponse> getCustomerEnginnerCall(@Header("Content-Type") String type, @Body GetCustomer_Engineer_Request getCustomerEngineerRequest);

    //  /////

    @POST("field_management/getlist_id")
    Call<GetFieldListResponse> getfieldListResponseCall(@Header("Content-Type") String type, @Body GetFieldListRequest getFieldListRequest);

        @POST("joininspection/getlist_id_test")
    Call<GetFieldListResponse> joinInspectionGetFieldListResponseCall(@Header("Content-Type") String type, @Body GetFieldListRequest getFieldListRequest);

//    @POST("data_store_management/create")
//    Call<FormDataStoreResponse> getformdataListResponseCall1(@Header("Content-Type") String type, @Body GetFieldListResponse getFieldListResponse);

    @POST("joininspection/create")
    Call<FormDataStoreResponse> joinInspectionCreateRequestCall(@Header("Content-Type") String type, @Body GetFieldListResponse getFieldListResponse);


    ///// Logout API

    @POST("service_userdetails/update_logout_time")
    Call<SuccessResponse> LogoutCall(@Header("Content-Type") String type , @Body LogoutRequest logoutRequest);

    /*Notification token update*/
    @POST("service_userdetails/mobile/update/fb_token")
    Call<SuccessResponse> fBTokenUpdateResponseCall(@Header("Content-Type") String type, @Body FbTokenRequest fbTokenRequest);

    /* Check Outstanding Jobs */
    @POST("service_userdetails/check_outstanding_job")
    Call<CheckOutstandingJobResponse> CheckOutstandingJobCall(@Header("Content-Type") String type, @Body CheckOutstandingJobRequest checkOutstandingJobRequest);

    /* Update Outstanding Jobs */
    @POST("service_userdetails/pending_job_pause")
    Call<SuccessResponse> UpdateOutstandingJobCall(@Header("Content-Type") String type, @Body UpdateOutstandingJobRequest updateOutstandingJobRequest);

    /* Notication List */
    @POST("pop_notification/getlist_by_mobile_no")
    Call<NotificationListResponse> NotificationListCall(@Header("Content-Type") String type, @Body NotificationListRequest notificationListRequest);

    @POST("notification/update_view_status")
    Call<SuccessResponse> NotificationupdateCall(@Header("Content-Type") String type, @Body NotificationListRequest notificationListRequest);


    @POST("notification/delete_notificaiton")
    Call<SuccessResponse> NotificationdeleteCall(@Header("Content-Type") String type, @Body NotificationListRequest notificationListRequest);

    // Skip Job Details API //

    @POST("breakdown_data_management/skip_job_detail")
    Call<SuccessResponse> SkipJobDetails(@Header("Content-Type") String type, @Body SkipJobDetailRequest skipJobDetailRequest);

    // PopUp Image API //

    @POST("pop_notification/check_image")
    Call<GetPopupImageResponse> getPopUpImage(@Header("Content-Type") String type, @Body GetPopUpImageRequest getPopUpImageRequest);


    @POST("pop_notification/update_status")
    Call<SuccessResponse> updatePopUpImage(@Header("Content-Type") String type, @Body UpdatePopImageRequest updatePopImageRequest);
}

