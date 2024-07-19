package landvibe.springintro.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {
    private Long id;
    private String name;
}
