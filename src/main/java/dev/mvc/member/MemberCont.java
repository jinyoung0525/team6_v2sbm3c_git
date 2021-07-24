package dev.mvc.member;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  public MemberCont(){
    System.out.println("-> MemberCont created.");
  }
  
  // http://localhost:9091/member/checkID.do?id=user1
  /**
  * ID 중복 체크, JSON 출력
  * @return
  */
  @ResponseBody
  @RequestMapping(value="/member/checkID.do", method=RequestMethod.GET ,
                         produces = "text/plain;charset=UTF-8" )
  public String checkID(String id) {
    System.out.println("id 확인 요청됨: " + id);
    
    int cnt = this.memberProc.checkID(id);
   
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
   
    return json.toString(); 
  }
  
  // http://localhost:9091/member/create.do
  /**
  * 등록 폼
  * @return
  */
  @RequestMapping(value="/member/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // webapp/member/create.jsp
   
    return mav; // forward
  }
  
  /**
   * 등록 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + memberVO.getId());
    
    memberVO.setGrade(11); // 기본 회원 가입 등록 11 지정
    
    int cnt= memberProc.create(memberVO);
    mav.addObject("cnt", cnt); // redirect parameter 적용
    mav.addObject("url", "create_msg"); // create_msg.jsp, redirect parameter 적용
    
    mav.setViewName("redirect:/member/msg.do"); // 새로고침 방지
    
    return mav;
  }
  
  /**
   * 새로고침을 방지하는 메시지 출력
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    
    // 등록 처리 메시지: create_msg --> /member/create_msg.jsp
    // 수정 처리 메시지: update_msg --> /member/update_msg.jsp
    // 삭제 처리 메시지: delete_msg --> /member/delete_msg.jsp
    mav.setViewName("/member/" + url); // forward
    
    return mav; // forward
  }
  
  /**
  * 목록 출력 가능
  * @param session
  * @return
  */
  @RequestMapping(value="/member/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
      List<MemberVO> list = memberProc.list();
      mav.addObject("list", list);

      mav.setViewName("/member/list"); // /webapp/WEB-INF/views/member/list.jsp
     

    
    
    return mav;
  }  
  
  /**
   * 회원 조회
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/read.do", method=RequestMethod.GET)
  public ModelAndView read(int memberno){
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    mav.setViewName("/member/read"); // /member/read.jsp
    
    return mav; // forward
  }
  
  /**
   * 회원 정보 수정 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/update.do", method=RequestMethod.POST)
  public ModelAndView update(MemberVO memberVO){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + memberVO.getId());
    
    int cnt= memberProc.update(memberVO);
    mav.addObject("cnt", cnt); // redirect parameter 적용
    mav.addObject("memberno", memberVO.getMemberno()); // redirect parameter 적용
    mav.addObject("url", "update_msg"); // update_msg.jsp, redirect parameter 적용

    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
  /**
   * 회원 삭제
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int memberno){
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    mav.setViewName("/member/delete"); // /member/delete.jsp
    
    return mav; // forward
  }
 
  /**
   * 회원 삭제 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int memberno){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + memberVO.getId());
    MemberVO memberVO = this.memberProc.read(memberno);
    
    int cnt= memberProc.delete(memberno);
    mav.addObject("cnt", cnt); // redirect parameter 적용
    mav.addObject("mname", memberVO.getMname()); // redirect parameter 적용
    mav.addObject("url", "delete_msg"); // delete_msg.jsp, redirect parameter 적용
    
    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
  /**
   * 패스워드를 변경합니다.
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView passwd_update(int memberno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/passwd_update");
    
    return mav;
  }
  
  /**
   * 패스워드 변경 처리
   * @param memberno 회원 번호
   * @param current_passwd 현재 패스워드
   * @param new_passwd 새로운 패스워드
   * @return
   */
  @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.POST)
  public ModelAndView passwd_update(int memberno, String current_passwd, String new_passwd){
    ModelAndView mav = new ModelAndView();
    
    // 현재 패스워드 검사
    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("memberno", memberno);
    map.put("passwd", current_passwd);
    
    int cnt = memberProc.passwd_check(map);
    int update_cnt = 0; // 변경된 패스워드 수
    
    if (cnt == 1) { // 현재 패스워드가 일치하는 경우
      map.put("passwd", new_passwd); // 새로운 패스워드를 저장
      update_cnt = memberProc.passwd_update(map); // 패스워드 변경 처리
      mav.addObject("update_cnt", update_cnt);  // 변경된 패스워드의 갯수    
    }

    mav.addObject("cnt", cnt); // 패스워드 일치 여부
    mav.addObject("url", "passwd_update_msg");
    
    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
//  /**
//  * 로그인 폼
//  * @return
//  */
//  http://localhost:9091/member/login.do 
//  @RequestMapping(value = "/member/login.do", 
//                            method = RequestMethod.GET)
//   public ModelAndView login() {
//   ModelAndView mav = new ModelAndView();
//  
//   mav.setViewName("/member/login_form");
//   return mav;
//  }

//  /**
//  * 로그인 처리
//  * @return
//  */
//  http://localhost:9091/member/login.do 
//  @RequestMapping(value = "/member/login.do", 
//                            method = RequestMethod.POST)
//  public ModelAndView login_proc(HttpSession session,
//                                                  String id, 
//                                                String passwd) {
//   ModelAndView mav = new ModelAndView();
//   Map<String, Object> map = new HashMap<String, Object>();
//   map.put("id", id);
//   map.put("passwd", passwd);
// 
//   int count = memberProc.login(map); // id, passwd 일치 여부 확인
//   if (count == 1) { // 로그인 성공
//     // System.out.println(id + " 로그인 성공");
//     MemberVO memberVO = memberProc.readById(id); // 로그인한 회원의 정보 조회
//     session.setAttribute("memberno", memberVO.getMemberno());
//     session.setAttribute("id", id);
//     session.setAttribute("mname", memberVO.getMname());
//   
//     mav.setViewName("redirect:/index.do"); // 시작 페이지로 이동  
//   } else {
//     mav.addObject("url", "login_fail_msg"); // login_fail_msg.jsp, redirect parameter 적용
//  
//     mav.setViewName("redirect:/member/msg.do"); // 새로고침 방지
//   }
//     
//   return mav;
//  }

  /**
  * 로그아웃 처리
  * @param session
  * @return
  */
  @RequestMapping(value="/member/logout.do", 
                            method=RequestMethod.GET)
  public ModelAndView logout(HttpSession session){
   ModelAndView mav = new ModelAndView();
   session.invalidate(); // 모든 session 변수 삭제
   
   mav.addObject("url", "logout_msg"); // logout_msg.jsp, redirect parameter 적용
   
   mav.setViewName("redirect:/member/msg.do"); // 새로고침 방지
   
   return mav;
  }
  
  /**
   * Cookie 기반 로그인 폼
   * @return
   */
  // http://localhost:9091/member/login.do 
  @RequestMapping(value = "/member/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login_cookie(HttpServletRequest request,
                                                @RequestParam(value="return_url", defaultValue="") String return_url ) {
    ModelAndView mav = new ModelAndView();
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String ck_id = ""; // id 저장
    String ck_id_save = ""; // id 저장 여부를 체크
    String ck_passwd = ""; // passwd 저장
    String ck_passwd_save = ""; // passwd 저장 여부를 체크

    if (cookies != null) {  // Cookie 변수가 있다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
        
        if (cookie.getName().equals("ck_id")){
          ck_id = cookie.getValue();                                 // Cookie에 저장된 id
        }else if(cookie.getName().equals("ck_id_save")){
          ck_id_save = cookie.getValue();                          // Cookie에 id를 저장 할 것인지의 여부, Y, N
        }else if (cookie.getName().equals("ck_passwd")){
          ck_passwd = cookie.getValue();                          // Cookie에 저장된 password
        }else if(cookie.getName().equals("ck_passwd_save")){
          ck_passwd_save = cookie.getValue();                  // Cookie에 password를 저장 할 것인지의 여부, Y, N
        }
      }
    }
    
    mav.addObject("ck_id", ck_id); 
    mav.addObject("ck_id_save", ck_id_save);
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);
    mav.addObject("return_url", return_url);
    
    mav.setViewName("/member/login_ck_form");
    return mav;
  }
   
  
  /**
   * Cookie 기반 로그인 처리
   * @param request Cookie를 읽기위해 필요
   * @param response Cookie를 쓰기위해 필요
   * @param session 로그인 정보를 메모리에 기록
   * @param id  회원 아이디
   * @param passwd 회원 패스워드
   * @param id_save 회원 아이디 Cookie에 저장 여부
   * @param passwd_save 패스워드 Cookie에 저장 여부
   * @return
   */
  // http://localhost:9091/member/login.do 
  @RequestMapping(value = "/member/login.do", 
                             method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(
                             HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session,
                             String id, String passwd,
                             @RequestParam(value="id_save", defaultValue="") String id_save,
                             @RequestParam(value="passwd_save", defaultValue="") String passwd_save,
                             @RequestParam(value="return_url", defaultValue="") String return_url) {
    ModelAndView mav = new ModelAndView();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("passwd", passwd);
    
    int count = memberProc.login(map);
    if (count == 1) { // 로그인 성공
      // System.out.println(id + " 로그인 성공");
      MemberVO memberVO = memberProc.readById(id);
      session.setAttribute("memberno", memberVO.getMemberno()); // 서버의 메모리에 기록
      session.setAttribute("id", id);
      session.setAttribute("mname", memberVO.getMname());
      session.setAttribute("grade", memberVO.getGrade());
      
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_id.setMaxAge(60 * 60 * 72 * 10); // 30 day, 초단위
        response.addCookie(ck_id); // id 저장
      } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setPath("/");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id); // id 저장
      }
      // id를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setPath("/");
      ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_passwd = new Cookie("ck_passwd", passwd);
        ck_passwd.setPath("/");
        ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setPath("/");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // passwd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setPath("/");
      ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------
      
      System.out.println("-> return_url: " + return_url);
      
      if (return_url.length() > 0) {   // ★
        mav.setViewName("redirect:" + return_url);  
      } else {
        mav.setViewName("redirect:/index.do");
      }
        
    } else {
      mav.addObject("url", "login_fail_msg");
      mav.setViewName("redirect:/member/msg.do"); 
    }
        
    return mav;
  }
  
  /**
   * Session test
   * http://localhost:9091/member/session.do
   * @param session
   * @return
   */
  @RequestMapping(value="/member/session.do", 
                             method=RequestMethod.GET)
  public ModelAndView session(HttpSession session){
    ModelAndView mav = new ModelAndView();
    
    mav.addObject("url", "session");
    mav.setViewName("redirect:/member/msg.do"); 
    
    return mav;
  }
    
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}