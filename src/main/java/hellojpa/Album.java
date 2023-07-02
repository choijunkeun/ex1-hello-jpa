package hellojpa;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
//@DiscriminatorValue("A") // 부모클래스에서 @DiscriminatorColumn을 선언했으니 테이블에 DTPYE이 생기고 그 안의 값을 default값(Album)이 아닌 A로 넣겠다는 뜻
public class Album extends Item{
    private String artist;
}
