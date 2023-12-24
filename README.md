# capacitor-facebook-events

Facebook events tracking in Capacitor applications

## Install

```bash
npm install capacitor-facebook-events
npx cap sync
```

## API

<docgen-index>

* [`setAdvertiserTrackingEnabled(...)`](#setadvertisertrackingenabled)
* [`logEvent(...)`](#logevent)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### setAdvertiserTrackingEnabled(...)

```typescript
setAdvertiserTrackingEnabled(options: { enabled: boolean; }) => Promise<void>
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ enabled: boolean; }</code> |

--------------------


### logEvent(...)

```typescript
logEvent(options: { event: string; params?: any; }) => Promise<void>
```

| Param         | Type                                          |
| ------------- | --------------------------------------------- |
| **`options`** | <code>{ event: string; params?: any; }</code> |

--------------------

</docgen-api>
