package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;

public interface MemberService {
    public void add(Member member);
    public Member findByTelephone(String telephone);
    List<Integer> findMemberCountByMonth(List<String> month);
}
