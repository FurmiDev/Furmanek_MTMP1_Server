package sk.feistu.furmi.androidserv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projectile {
    private float xPos;
    private float yPos;
    private float timeVal;
}
