package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // null 이 반환될 가능성이 있으면 optional 로 감싸도록 처리한다
    }

    @Override
    public Optional<Member> findByName(String name) {
        store.values().stream()
                .filter(member -> member.getName().equals(name).findAny());
        // 람다로 조회 : member.getName 이 파라미터로 넘어온 name이랑 같은 경우에만 필터링이 되고, 찾으면 반환한다.
        // findAny : 하나라도 찾는 것
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store 의 value 가 member 이므로, values 를 반환
    }
}
