package com.netsong7.test1;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Inject
	private SqlSession sqlSession;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	private static final String namespace = "com.netsong7.mapper.MemberMapper";
	
	@Override
	public void addUser(MemberVO member) {
		member.setUserpw(this.bcryptPasswordEncoder.encode(member.getUserpw()));
		sqlSession.insert(namespace + ".insert", member);
		Object[] obj = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray();
		//System.out.println("test : " + obj[0]);
		Map map = new HashMap();
		map.put("userid",member.getUserid());
		map.put("role", obj[0].toString());
		System.out.println("권한 : " + map.get("role").toString().length());
		System.out.println("이름 : " + member.getUsername());
		sqlSession.insert(namespace + ".insertAuth", map);
	}
	
	@Override
	public MemberVO login(MemberVO member) {
		//return sqlSession.selectOne(namespace + ".login", member);
		return null;
	}
	
	@Override
	public int editAccount(MemberVO member) {
		return sqlSession.update(namespace + ".update", member);
	}

	@Override
	public int changePasswd(String currentPasswd, String newPasswd, String userid) {
		Map map = new HashMap();
		map.put("currentPasswd", currentPasswd);
		map.put("newPasswd", newPasswd);
		map.put("userid", userid);
		return sqlSession.update(namespace + ".updatePasswd", map);
	}

	@Override
	public void bye(String userid) {
		sqlSession.delete(namespace + ".delete", userid);
	}
}
